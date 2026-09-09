FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/telemetry-analyzer-0.0.1-SNAPSHOT.jar app.jar
COPY ../logs.txt /app/logs.txt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]