package com.example.temperatura_mikroservis.model;

import java.util.concurrent.ThreadLocalRandom;

public class Temperature {

    private static final Double min = 0.0;
    private static final Double max = 100.0;
    private final Double measurement;

    public Temperature() {
        this.measurement = ThreadLocalRandom.current().nextDouble(min, max);
    }
    public Temperature(Double measurement) {
        this.measurement = measurement;
    }

    public static Double getMin() {
        return min;
    }
    public static Double getMax() {
        return max;
    }

    public Double getMeasurement() {
        return measurement;
    }
}
