package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.microservice_classes.Temperature;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.Queue;

public class TemperatureRule extends Rule {

    private static final int numberOfMeasurements = 100;
    private Queue<Temperature> recentMeasurements;
    private int counter;

    public TemperatureRule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, limit, null, null, inARow);
        recentMeasurements = new LinkedList<>();
        this.counter = 0;
    }

    @Override
    public String getDescription() {
        return "Temperatura ne smije prelaziti " + this.getLimit() + " stepeni";
    }

    public boolean followsRule(Temperature measurement){
        if(measurement.getMeasurement() < this.getLimit()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendAlert(Temperature measurement){
        if(this.recentMeasurements.size() >= numberOfMeasurements){
            this.recentMeasurements.poll();
        }
        this.recentMeasurements.add(measurement);

        Queue<Temperature> temp = this.recentMeasurements;
        while(!temp.isEmpty()){
            if(followsRule(temp.poll())){
                counter = 0;
                return false;
            }
            counter++;
        }

        if(counter >= this.getInARow()){
            return true;
        }
        return false;
    }
}
