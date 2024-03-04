#FROM openjdk:11-bullseye
#LABEL author=menu-flash
#COPY "./target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
#ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]

FROM openjdk:11-bullseye AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests
FROM openjdk:11-bullseye
LABEL author=menu-flash
COPY --from=build "target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]
