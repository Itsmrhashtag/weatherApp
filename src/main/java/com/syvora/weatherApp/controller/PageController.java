package com.syvora.weatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.syvora.weatherApp.services.ApiService;
import com.syvora.weatherApp.services.S3Service;

@Controller
public class PageController {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ApiService apiService;

    @GetMapping("/display-json")
    public String displayJson(@RequestParam String city, Model model) {
        try {
            String weatherData = apiService.getWeatherData(city);

            String fileName = "weather-data.json";
            
            s3Service.uploadToS3(fileName, weatherData);

            JsonNode jsonData = s3Service.fetchJsonFromS3(fileName);

            model.addAttribute("jsonData", jsonData);

            return "displayWeather";
        } catch (Exception e) {
            model.addAttribute("error", "Could not fetch or save weather data for city: " + city);
            return "errorpage";
        }
    }


    @GetMapping("/")
    public String page() {
        return "index"; 
    }
    @GetMapping("/weather-form")
    public String showWeatherForm() {
        return "weatherForm";
    }
    
//    @GetMapping("/fetch-weather")
//    public String fetchWeather(@RequestParam String city, Model model) {
//        try {
//            String weatherData = apiService.getWeatherData(city);
//            model.addAttribute("weatherData", weatherData);
//            return "displayWeather";
//        } catch (Exception e) {
//            model.addAttribute("error", "Could not fetch weather data for city: " + city);
//            return "weatherForm";
//        }
//    }
}
