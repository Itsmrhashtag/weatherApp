# WeatherApp

WeatherApp is a Spring Boot application that allows users to check the weather by entering a city name. The app provides a simple interface to input a city name, and it redirects to a page displaying the weather details for that city.


## How to Run the Application

### Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

### Steps to Run the Application

1. **Clone the Repository**

   Clone the repository from GitHub:

   ```bash
   git clone https://github.com/your-username/weatherapp.git
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


## Features

- Home page at `http://localhost:8080/`.
- Weather form at `http://localhost:8080/weather-form` to enter a city name.
- Displays weather information by redirecting to `http://localhost:8080/display-json?city=<cityname>`.
- **Important Feature**
-    `http://localhost:8080/create-bucket` for creating bucket if not present in aws 
