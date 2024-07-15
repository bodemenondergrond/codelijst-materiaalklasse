'use strict';
import {  generate_skos } from 'maven-metadata-generator-npm';
import {
    ttl,
    nt,
    jsonld,
    csv,
    xsd
} from './utils/variables.js';

generate_skos(ttl, jsonld, nt, csv, xsd);

