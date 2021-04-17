FROM adoptopenjdk/openjdk11-openj9:x86_64-alpine-jdk-11.0.5_10_openj9-0.17.0-slim
ENV LANG C.UTF-8

# Set ENV variables
ENV PORT=9011 

# Add JAR file and run it as entrypoint
ADD target/bankAccount-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 9011

# Derivando da imagem oficial do MySQL
FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root

EXPOSE 3306
