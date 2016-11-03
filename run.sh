#!/usr/bin/env bash
# Start spring-boot with local properties
mvn spring-boot:run -Drun.jvmArguments='-Dspring.config.location=classpath:/application-local.properties'

