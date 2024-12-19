package com.syvora.weatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syvora.weatherApp.services.ApiService;
import com.syvora.weatherApp.services.S3Service;

@RestController
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ApiService apiService;
    
    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @GetMapping("/create-bucket")
    public String createBucket() {
        s3Service.createBucket(bucketName);
        return "Bucket created: " + bucketName;
    }
    

    @GetMapping("/save-weather-data")
    public String saveWeatherData(@RequestParam String city) {
        String weatherData = apiService.getWeatherData(city);

        String fileName = "weather-data.json";

        if (s3Service.doesFileExist(fileName)) {
            s3Service.uploadToS3(fileName, weatherData);
            return "Existing file updated in S3 with file name: " + fileName;
        } else {
            s3Service.uploadToS3(fileName, weatherData);
            return "New weather data saved to S3 with file name: " + fileName;
        }
    }
}
