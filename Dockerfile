FROM maven:3.9.16-eclipse-temurin-17-alpine AS builder
WORKDIR /build
COPY pom.xml .
COPY ./logging-starter/target/*.jar ./starter/logging.jar
RUN mvn -B install:install-file \
    -Dfile=starter/logging.jar \
    -DgroupId=org.example \
    -DartifactId=logging-starter \
    -Dversion=1.0-SNAPSHOT \
    -Dpackaging=jar
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]