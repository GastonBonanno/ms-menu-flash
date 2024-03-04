#FROM openjdk:11-bullseye
#LABEL author=menu-flash
#COPY "./target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
#ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]

#FROM openjdk:17-alpine AS build
#COPY . .
#RUN mvn clean install
#FROM openjdk:17-alpine
#LABEL author=menu-flash
#COPY --from=build "target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]



#FROM ubuntu:latest as build
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .
#RUN mvn clean install
#
#FROM openjdk:17-jdk-slim
#LABEL author=menu-flash
#COPY --from=build "target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]



#
# Build stage
#
FROM maven:3.8.2-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/menu-flash-0.0.1-SNAPSHOT.jar /usr/local/lib/menuflash-app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/usr/local/lib/menuflash-app.jar"]
