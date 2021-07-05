package com.example.cpu_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CPUMeasurement {
    private static final Double min = 0.0;
    private static final Double max = 100.0;
    private final List<Double> measurement;
    private Integer numberOfProcessors;

    public CPUMeasurement(@JsonProperty("numberOfProcessors")Integer numberOfProcessors) {
        this.measurement = new ArrayList<>();
        this.numberOfProcessors = numberOfProcessors;
        for (int i = 0; i < this.numberOfProcessors.intValue(); i++) {
            this.measurement.add(ThreadLocalRandom.current().nextDouble(min, max));
        }
    }

    public CPUMeasurement(List<Double> measurement) {
        this.measurement = measurement;
    }

    public List<Double> getMeasurement() {
        return measurement;
    }

    public static Double getMin() {
        return min;
    }

    public static Double getMax() {
        return max;
    }

    public Integer getNumberOfProcessors() {
        return numberOfProcessors;
    }
}
