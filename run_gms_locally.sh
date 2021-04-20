#!/bin/bash

export EBEAN_DATASOURCE_USERNAME=datahub
export EBEAN_DATASOURCE_PASSWORD=datahub
export EBEAN_DATASOURCE_HOST=localhost:5432
export EBEAN_DATASOURCE_URL=jdbc:postgresql://localhost:5432/datahub
export EBEAN_DATASOURCE_DRIVER=org.postgresql.Driver
export KAFKA_BOOTSTRAP_SERVER=localhost:29092,localhost:9092
export KAFKA_SCHEMAREGISTRY_URL=http://localhost:8081
export ELASTICSEARCH_HOST=localhost
export ELASTICSEARCH_PORT=9200
export NEO4J_HOST=http://localhost:7474
export NEO4J_URI=bolt://neo4j
export NEO4J_USERNAME=neo4j
export NEO4J_PASSWORD=datahub

./gradlew :gms:war:run

