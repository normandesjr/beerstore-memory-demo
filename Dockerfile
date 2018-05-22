FROM openjdk:8-jdk-alpine

ADD target/beerstore.jar /usr/app/app.jar

WORKDIR /usr/app

EXPOSE 8080
CMD java $JAVA_OPTIONS -jar app.jar
