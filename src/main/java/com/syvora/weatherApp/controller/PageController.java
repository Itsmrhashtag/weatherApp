package com.syvora.weatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.syvora.weatherApp.services.S3Service;

@Controller
public class PageController {

	@Autowired
    private S3Service s3Service;

    @GetMapping("/display-json")
    public String displayJson(@RequestParam String key, Model model) {
    	System.out.println("----------------------");
        JsonNode jsonData = s3Service.fetchJsonFromS3(key);

        model.addAttribute("jsonData", jsonData);
        return "jsonDisplay";
    }
    @GetMapping("/page")
    public String page() {
    	return "index";
    }
}
