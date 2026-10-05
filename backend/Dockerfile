# Build with Java 21; Maven is supplied by the image, not the host machine.
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn -B verify

# Keep only the JVM and executable application in the runtime image.
FROM eclipse-temurin:21.0.10_7-jre-alpine
WORKDIR /app
RUN addgroup -S studex && adduser -S studex -G studex
COPY --from=build --chown=studex:studex /app/target/studex-backend.jar app.jar
USER studex
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
