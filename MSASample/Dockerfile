FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/MSASample-0.0.1-SNAPSHOT.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
