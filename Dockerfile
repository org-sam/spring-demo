FROM public.ecr.aws/docker/library/openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ./build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
