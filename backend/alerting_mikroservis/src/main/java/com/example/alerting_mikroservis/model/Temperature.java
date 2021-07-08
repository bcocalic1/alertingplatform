package com.example.alerting_mikroservis.model;

import java.util.ArrayList;
import java.util.List;

public class Temperature {
    private final Double measurement;

    public Temperature() {
        this.measurement = 0.0;
    }

    public Temperature(Temperature measurement){
        this.measurement = measurement.getMeasurement();
    }

    public Temperature(Double measurement) {
        this.measurement = measurement;
    }

    public Double getMeasurement() {
        return measurement;
    }
}
