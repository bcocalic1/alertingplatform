package com.example.cpu_mikroservis.service;

import com.example.cpu_mikroservis.dao.MeasurementDaoInterface;
import com.example.cpu_mikroservis.model.CPUMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementDaoInterface measurementDaoInterface;

    @Autowired
    public MeasurementService(@Qualifier("measurement") MeasurementDaoInterface measurementDaoInterface) {
        this.measurementDaoInterface = measurementDaoInterface;
    }

    public void addMeasurement(CPUMeasurement measurement){
        measurementDaoInterface.addMeasurement(measurement);
    }

    public List<CPUMeasurement> getMeasurements() {
        return measurementDaoInterface.getMeasurements();
    }
}
