package com.example.alerting_mikroservis;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/cpu")
    public Measurement getMeasurement(){
        RestTemplate restTemplate = new RestTemplate();
        return new Measurement(restTemplate.getForObject("http://localhost:8083/api/v1/cpu/get", Measurement.class));
    }

    @PostMapping("/cpu")
    public String dummy(@RequestBody String test){
        System.out.println("ok");
        return test;
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert("Ime 1", "servis 1", "LOW", "Opis"));
        alerts.add(new Alert("ime 2", "servis 2", "MEDIUM", "opis 2"));
        alerts.add(new Alert("ime 3", "servis 3", "HIGH", "opis 3"));
        return alerts;
    }

}
