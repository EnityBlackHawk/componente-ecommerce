FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build
RUN unzip build/distributions/componente-ecommerce-0.0.1-SNAPSHOT.zip -d /app/out


FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/out/componente-ecommerce-0.0.1-SNAPSHOT /app

RUN apk add --no-cache curl

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]

