package ru.skillfactory.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemoController {


    @CrossOrigin(origins = "https://example.com",
    methods = {RequestMethod.GET, RequestMethod.POST},
    allowedHeaders = {"Authorization", "Content-Type"},
    allowCredentials = "true")
    @GetMapping("/v2/greeting")
    public String newGreeting() {
        return "Hello from new server";
    }


    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"},
    methods = {RequestMethod.GET, RequestMethod.POST}, allowCredentials = "true")
    @GetMapping("/greeting")
    public String greeting() {
        return "Hello from server";
    }

}
