package com.example.alerting_mikroservis;

import java.util.ArrayList;
import java.util.List;

public class CPUMeasurement {
    private static final Double min = 0.0;
    private static final Double max = 100.0;
    private final List<Double> measurement;
    private Integer numberOfProcessors;

    public CPUMeasurement() {
        this.measurement = new ArrayList<>();
    }

    public CPUMeasurement(CPUMeasurement measurement){
        this.measurement = measurement.getMeasurement();
        this.numberOfProcessors = measurement.getMeasurement().size();
    }

    public CPUMeasurement(List<Double> measurement) {
        this.measurement = measurement;
    }

    public List<Double> getMeasurement() {
        return measurement;
    }
}
