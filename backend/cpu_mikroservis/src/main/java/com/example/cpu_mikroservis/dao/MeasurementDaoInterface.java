package com.example.cpu_mikroservis.dao;

import com.example.cpu_mikroservis.model.Measurement;

import java.util.List;

public interface MeasurementDaoInterface {

    void addMeasurement(Measurement measurement);

    default void addMeasurement(Integer numberOfProcessors){
        addMeasurement(new Measurement(numberOfProcessors));
    }

    List<Measurement> getMeasurements();
}
