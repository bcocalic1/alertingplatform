package com.example.cpu_mikroservis.dao;

import com.example.cpu_mikroservis.model.Measurement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("measurement")
public class MeasurementDao implements MeasurementDaoInterface{

    private List<Measurement> measurementList;

    public MeasurementDao() {
        this.measurementList = new ArrayList<>();
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        measurementList.add(measurement);
    }

    @Override
    public List<Measurement> getMeasurements() {
        return measurementList;
    }
}
