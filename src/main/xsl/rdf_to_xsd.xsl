<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    xmlns:dc="http://purl.org/dc/elements/1.1/"    
    exclude-result-prefixes="rdf xsl dc"
    version="1.0">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <xs:schema>
            <xs:simpleType >
                <xsl:attribute name="name">
                    <xsl:value-of select="rdf:RDF/rdf:Description[./rdf:type/@rdf:resource='http://www.w3.org/2004/02/skos/core#ConceptScheme']/dc:identifier" />
                </xsl:attribute>                  
                <xs:restriction base="xs:string">
                    <xsl:apply-templates select="rdf:RDF/rdf:Description[./rdf:type/@rdf:resource='http://www.w3.org/2004/02/skos/core#Concept']"/>
                </xs:restriction>
            </xs:simpleType>
        </xs:schema>
    </xsl:template>
    <xsl:template match="rdf:Description">
        <xs:enumeration>
            <xsl:attribute name="value">
                <xsl:value-of select="@rdf:about" />
            </xsl:attribute>
        </xs:enumeration>
    </xsl:template>
</xsl:stylesheet>

