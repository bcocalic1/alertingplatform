package com.example.cpu_mikroservis.api;

import com.example.cpu_mikroservis.model.CPUMeasurement;
import com.example.cpu_mikroservis.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/cpu")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(@RequestBody MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public void addMeasurement(@RequestBody CPUMeasurement measurement){
        measurementService.addMeasurement(measurement);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CPUMeasurement> request = new HttpEntity<>(measurement);
        CPUMeasurement response = restTemplate.postForObject("http://localhost:8080/api/v1/cpu", request, CPUMeasurement.class);
        System.out.println(response);
    }

    @GetMapping("/get")
    public CPUMeasurement getMeasurements(){
        int last = measurementService.getMeasurements().size() - 1;
        return measurementService.getMeasurements().get(last);
    }
}
