package com.example.temperatura_mikroservis.service;

import com.example.temperatura_mikroservis.dao.TemperatureDaoInterface;
import com.example.temperatura_mikroservis.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {
    private final TemperatureDaoInterface temperatureDaoInterface;

    @Autowired
    public TemperatureService(@Qualifier("measurement") TemperatureDaoInterface measurementDaoInterface) {
        this.temperatureDaoInterface = measurementDaoInterface;
    }

    public void addMeasurement(Temperature measurement){
        temperatureDaoInterface.addMeasurement(measurement);
    }

    public List<Temperature> getMeasurements() {
        return temperatureDaoInterface.getMeasurements();
    }
}
