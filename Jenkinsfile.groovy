@Library('Cumulus@1.2-stable') _
pipeline {
    agent {
        kubernetes {
            inheritFrom 'jenkins-jenkins-agent'
            yaml podBuilder.from([maven.podSpec(21), trivy])
        }
    }
    stages {
        stage("Setup") {
            steps {
                script {
                    properties([versions.releaseParameters()])
                }
            }
        }

        stage('Build and deploy') {
           when { 
                allOf {
                    branch pattern: 'main|release.*|develop.*', comparator: 'REGEXP' 
                    expression { !versions.isRelease() }    
                }
            }
           steps {
                script {
                    maven.goal([goal: 'deploy', skipTests: true])
                }
           }
        }
        stage('Build only') {
           when { branch pattern: 'FEATURE.*', comparator: 'REGEXP' }
           steps {
                script {
                    maven.goal([goal: 'verify', skipTests: true])
                }
            }
        }

        stage('Trivy') {
            when { 
                allOf {
                    expression { git.notSkipCi() } 
                }
            }
            steps {
                script {
                    trivy.scanFilesystem([targetPath: 'pom.xml',
                                          extraArgs: ' --timeout 15m'])
                }
            }
        }
        
        stage("Release") {
            // not all branches can be released
            when {
                allOf {
                    expression { git.notSkipCi() }
                    expression { versions.isRelease() }
                    branch pattern: 'main|release.*', comparator: 'REGEXP'
                }
            }
            steps {
                script {
                    def currentVersion = maven.version()
                    env.CURRENTVERSION = currentVersion

                    def version = versions.bump(currentVersion)
                    git.validateTag(version)
                    maven.validateVersion(version)
                    env.RELEASEVERSION = version
                    echo "${env.CURRENTVERSION}} will be released as ${env.RELEASEVERSION}"

                    maven.goal([goal     : 'release:clean release:prepare release:perform',
                                version  : env.RELEASEVERSION,
                                skipTests: true
                    ])
                }
            }
        }
    }
    post {
        always {
            script {
                pipelineSummary([sonarProjectKey: env.BRANCH_IS_PRIMARY && !versions.isRelease() ? env.SONAR_PROJECT_KEY : null])
            }
        }
    }
}
