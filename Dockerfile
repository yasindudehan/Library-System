FROM maven:3.9.5-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src

# Build JAR inside container
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]