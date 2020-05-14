# build jar
FROM maven:3-jdk-11 as builder
WORKDIR /app
COPY . /app
RUN mvn clean package

# run application
FROM openjdk:11
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]