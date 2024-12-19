# WeatherApp

WeatherApp is a Spring Boot application that allows users to check the weather by entering a city name. The app provides a simple interface to input a city name, and it redirects to a page displaying the weather details for that city.

## Features

- Home page at `http://localhost:8080/`.
- Weather form at `http://localhost:8080/weather-form` to enter a city name.
- Displays weather information by redirecting to `http://localhost:8080/display-json?city=<cityname>`.

## How to Run the Application

### Prerequisites

- [Docker](https://www.docker.com/get-started) installed on your machine.

### Steps to Run the Application

1. **Clone the Repository**

   Clone the repository from GitHub:

   ```bash
   git clone https://github.com/your-username/weatherapp.git
   cd weatherapp
