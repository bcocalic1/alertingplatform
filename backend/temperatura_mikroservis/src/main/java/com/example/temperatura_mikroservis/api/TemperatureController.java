package com.example.temperatura_mikroservis.api;

import com.example.temperatura_mikroservis.model.Temperature;
import com.example.temperatura_mikroservis.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/temperature")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(@RequestBody TemperatureService measurementService) {
        this.temperatureService = measurementService;
    }

    @PostMapping
    public void addMeasurement(@RequestBody Temperature measurement){
        temperatureService.addMeasurement(measurement);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Temperature> request = new HttpEntity<>(measurement);
        Temperature response = restTemplate.postForObject("http://localhost:8080/api/v1/cpu", request, Temperature.class);
        System.out.println(response);
    }
}
