FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/discoveryservice.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]