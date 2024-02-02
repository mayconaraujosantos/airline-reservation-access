FROM adoptopenjdk:17-jdk-hotspot AS builder

# Definir variáveis de ambiente para construir o projeto
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false"

COPY . /app
WORKDIR /app

RUN ./gradlew clean build -x test

FROM adoptopenjdk:17-jre-hotspot

COPY --from=builder /app/ticket-collector/build/libs/*.jar /app/ticket-collector.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/ticket-collector-1.0.0.jar"]
