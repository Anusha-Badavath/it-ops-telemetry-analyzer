# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /build/target/telemetry-analyzer-0.0.1-SNAPSHOT.jar app.jar
COPY logs.txt /app/logs.txt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]