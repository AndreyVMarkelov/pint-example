#!/bin/sh

echo "********************************************************"
echo "Starting the Simple Service Discovery"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/simple-sd/@project.build.finalName@.jar
