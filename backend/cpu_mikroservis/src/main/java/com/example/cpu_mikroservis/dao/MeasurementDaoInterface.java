package com.example.cpu_mikroservis.dao;

import com.example.cpu_mikroservis.model.CPUMeasurement;

import java.util.List;

public interface MeasurementDaoInterface {

    void addMeasurement(CPUMeasurement measurement);

    default void addMeasurement(Integer numberOfProcessors){
        addMeasurement(new CPUMeasurement(numberOfProcessors));
    }

    List<CPUMeasurement> getMeasurements();
}
