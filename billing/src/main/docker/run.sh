#!/bin/sh

echo "********************************************************"
echo "Starting the Billing"
echo "********************************************************"

echo "********************************************************"
echo "Waiting for the Simple Service Discovery to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z simple-sd $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Simple Service Discovery has started"

java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/billing/@project.build.finalName@.jar
