package com.example.temperatura_mikroservis.dao;

import com.example.temperatura_mikroservis.model.Temperature;
import java.util.List;

public interface TemperatureDaoInterface {
    void addMeasurement(Temperature measurement);

    default void addMeasurement(){
        addMeasurement(new Temperature());
    }

    List<Temperature> getMeasurements();
}
