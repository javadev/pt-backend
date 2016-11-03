#!/bin/bash

set -e

echo
echo "----------- Build Java with maven ------------"
mvn clean install
