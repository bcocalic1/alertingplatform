package com.example.cpu_mikroservis.api;

import com.example.cpu_mikroservis.model.Measurement;
import com.example.cpu_mikroservis.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    }

    @GetMapping("/get")
    public Measurement getMeasurements(){
        int last = measurementService.getMeasurements().size() - 1;
        return measurementService.getMeasurements().get(last);
    }
}
