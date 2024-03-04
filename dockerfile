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

FROM ubuntu:latest as build
RUN apt-get update
RUN apt-get install openjdk:11-bullseye -y
COPY . .
RUN mvn clean install

FROM openjdk:11-bullseye
LABEL author=menu-flash
COPY --from=build "target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]
