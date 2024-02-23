FROM openjdk:11-bullseye
LABEL author=menu-flash
COPY "./target/menu-flash-0.0.1-SNAPSHOT.jar" "menuflash-app.jar"
ENTRYPOINT ["java", "-jar", "/menuflash-app.jar"]
