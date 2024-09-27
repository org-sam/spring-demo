FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY  build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]