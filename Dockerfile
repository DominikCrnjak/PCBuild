# koristimo službenu OpenJDK 17 sliku kao osnovu
FROM amazoncorretto:21-alpine AS build
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
