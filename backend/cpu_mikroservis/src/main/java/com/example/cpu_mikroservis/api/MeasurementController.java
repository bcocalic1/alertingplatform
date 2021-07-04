package com.example.cpu_mikroservis.api;

import com.example.cpu_mikroservis.model.CPUMeasurement;
import com.example.cpu_mikroservis.service.MeasurementService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cpu")
public class MeasurementController {
    private final MeasurementService measurementService;
    @Autowired
    private RabbitTemplate rabbitTemplate = new RabbitTemplate();

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

    @GetMapping("/cpu")
    public List<CPUMeasurement> getMeasurements(){
        return this.measurementService.getMeasurements();
    }
}
