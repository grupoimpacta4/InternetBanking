FROM openjdk:8-jdk-alpine
ENV LANG C.UTF-8

RUN apk add --update bash

COPY ./target/*.jar /app/app.jar

CMD java -jar /app/app.jar

