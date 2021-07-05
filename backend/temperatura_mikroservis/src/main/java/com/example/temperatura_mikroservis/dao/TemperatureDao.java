package com.example.temperatura_mikroservis.dao;

import com.example.temperatura_mikroservis.model.Temperature;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository("measurement")
public class TemperatureDao implements com.example.temperatura_mikroservis.dao.TemperatureDaoInterface {

    private List<Temperature> measurementList;

    public TemperatureDao() {
        this.measurementList = new ArrayList<>();
    }

    @Override
    public void addMeasurement(Temperature measurement) {
        measurementList.add(measurement);
    }

    @Override
    public List<Temperature> getMeasurements() {
        return measurementList;
    }
}
