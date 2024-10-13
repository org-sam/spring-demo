# FROM public.ecr.aws/docker/library/openjdk:17-jdk-alpine
# ARG JAR_FILE=target/*.jar
# COPY  ./artifacts/libs/*-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]


FROM alpine:3.19
WORKDIR /app
EXPOSE 5000