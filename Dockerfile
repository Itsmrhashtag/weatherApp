# Use an official Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-17 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Run Maven to build the project
RUN mvn clean install -DskipTests

# Use an official Java runtime as a base image for the final image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the Maven build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
