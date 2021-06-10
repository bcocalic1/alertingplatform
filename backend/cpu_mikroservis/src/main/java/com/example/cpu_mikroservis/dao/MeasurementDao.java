package com.example.cpu_mikroservis.dao;

import com.example.cpu_mikroservis.model.CPUMeasurement;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("measurement")
public class MeasurementDao implements MeasurementDaoInterface{

    private List<CPUMeasurement> measurementList;

    public MeasurementDao() {
        this.measurementList = new ArrayList<>();
    }

    @Override
    public void addMeasurement(CPUMeasurement measurement) {
        measurementList.add(measurement);
    }

    @Override
    public List<CPUMeasurement> getMeasurements() {
        return measurementList;
    }
}
