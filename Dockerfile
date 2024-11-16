FROM openjdk:17-ea-11-jdk-slim
WORKDIR /app
COPY build/libs/admin-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java", "-jar", "app.war"]