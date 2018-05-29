FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} /usr/app/app.jar

WORKDIR /usr/app

EXPOSE 8080
CMD java $JAVA_OPTIONS -jar app.jar
