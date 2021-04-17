FROM openjdk:8-jdk-alpine 
ENV LANG C.UTF-8

# Set ENV variables
ENV PORT=9005 

# Add JAR file and run it as entrypoint
ADD target/login-service.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 9005

# Derivando da imagem oficial do MySQL
FROM mysql:8.0
 

ENV MYSQL_ROOT_PASSWORD=root

EXPOSE 3306