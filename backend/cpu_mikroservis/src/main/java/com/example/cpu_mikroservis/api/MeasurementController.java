package com.example.cpu_mikroservis.api;

import com.example.cpu_mikroservis.model.Measurement;
import com.example.cpu_mikroservis.service.MeasurementService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cpu")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(@RequestBody MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public void addMeasurement(@RequestBody Measurement measurement){
        measurementService.addMeasurement(measurement);

        /*
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Measurement> request = new HttpEntity<>(measurement, headers);
        try{
            restTemplate.postForObject("http://localhost:8080/cpu", request, Measurement.class);
        }catch (Exception e){
            System.out.println("Nije se poslalo " + e.getMessage());
        }*/


    }


    @PostMapping("/test")
    public void addMeasurement(@JsonProperty("integer")Integer integer, @JsonProperty("string")String string){
        System.out.println("int: " + integer + ", string: " + string);
    }

    @GetMapping
    public List<Measurement> getMeasurements(){
        return measurementService.getMeasurements();
    }
}
