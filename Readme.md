# WeatherApp

WeatherApp is a Spring Boot application that allows users to check the weather by entering a city name. The app provides a simple interface to input a city name, and it redirects to a page displaying the weather details for that city.


## How to Run the Application Using Docker

### Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

### Steps to Run the Application

1. **Clone the Repository**

   Clone the repository from GitHub:

   ```bash
   git clone https://github.com/Itsmrhashtag/weatherApp.git
   cd weatherapp

2. **Update in application.properties file**

   Code to use in `src/main/resources/application.properties`:

   ```bash
   spring.application.name=weatherApp
   aws.s3.region=us-east-2
   aws.access-key={access-key}
   aws.secret-key={secret-key}
   aws.s3.bucket.name=weatherapp122
   
   weather.api.url=https://api.openweathermap.org/data/2.5/weather
   weather.api.key={api.key}
   
3. **Build the Docker Image**

   Build the Docker image for the application using the following command:

   ```bash
   docker build -t weatherapp .

4. **Run the Application**

   After the image is built, run the application in Docker:

   ```bash
   docker run -p 8080:8080 weatherapp

## How to Run the Application Not Using Docker

### Prerequisites

- Java installed in System

### Steps to Run the Application
1. **Clone the Repository**

   Clone the repository from GitHub:

   ```bash
   git clone https://github.com/Itsmrhashtag/weatherApp.git
   cd weatherapp

2. **Update in application.properties file**

   Code to use in `src/main/resources/application.properties`:

   ```bash
   spring.application.name=weatherApp
   aws.s3.region=us-east-2
   aws.access-key={access-key}
   aws.secret-key={secret-key}
   aws.s3.bucket.name=weatherapp122
   
   weather.api.url=https://api.openweathermap.org/data/2.5/weather
   weather.api.key={api.key}

3. **Use any IDE like Intelij or Eclipse**
   Import project as maven project in IDE.
   Run `WeatherAppApplication.java` file present in `src\main\java\com\syvora\weatherApp`.

## Features
- Home page at `http://localhost:8080/`.
- Weather form at `http://localhost:8080/weather-form` to enter a city name.
- Displays weather information by redirecting to `http://localhost:8080/display-json?city=<cityname>`.
- **Important Feature**
   `http://localhost:8080/create-bucket` for creating bucket if not present in aws
     By this a bucket will be created  as name mention in `src/main/resources/application.properties` file `aws.s3.bucket.name=weatherapp122`
## Github Actions
   This project includes a CI/CD pipeline using GitHub Actions to automate the Docker image build and deployment process.

### Workflow Overview
 **Triggers:** Runs on every push and pull request to the main branch.
 **Steps:**
1. Checkout the source code from the repository.
2. Log in to Docker Hub using secrets.
3. Build the Docker image for the WeatherApp.
4. Tag and push the image to Docker Hub.
5. Optionally, run the container for testing purposes.
   This ensures that the application is always up-to-date and ready to be deployed efficiently.
