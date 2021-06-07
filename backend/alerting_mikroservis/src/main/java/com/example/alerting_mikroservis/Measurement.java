package com.example.alerting_mikroservis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Measurement {
    private static final Double min = 0.0;
    private static final Double max = 100.0;
    private final List<Double> measurement;
    private Integer numberOfProcessors;

    public Measurement() {
        System.out.println("Correct constructor");
        this.measurement = new ArrayList<>();
    }

    public Measurement(Measurement measurement){
        this.measurement = measurement.getMeasurement();
        this.numberOfProcessors = measurement.getMeasurement().size();
    }

    public Measurement(List<Double> measurement) {
        this.measurement = measurement;
    }

    public List<Double> getMeasurement() {
        return measurement;
    }
}
