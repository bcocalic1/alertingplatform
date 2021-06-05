package com.example.cpu_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Measurement {
    private static final Double min = 0.0;
    private static final Double max = 100.0;
    private final List<Double> measurement;
    private Integer numberOfProcessors;

    public Measurement(@JsonProperty("numberOfProcessors")Integer numberOfProcessors) {
        System.out.println("number of processors(received from postman)1 = " + numberOfProcessors);
        this.measurement = new ArrayList<>();
        this.numberOfProcessors = numberOfProcessors;
        System.out.println("number of processors(received from postman)2 = " + numberOfProcessors);
        for (int i = 0; i < this.numberOfProcessors.intValue(); i++) {
            this.measurement.add(ThreadLocalRandom.current().nextDouble(min, max));
        }
    }

    public Measurement(List<Double> measurement) {
        this.measurement = measurement;
    }

    public List<Double> getMeasurement() {
        return measurement;
    }
}
