FROM openjdk:8-jdk-alpine

RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/housekeeper

COPY @project.build.finalName@.jar /usr/local/housekeeper/
COPY run.sh run.sh

RUN chmod +x run.sh
CMD ./run.sh
