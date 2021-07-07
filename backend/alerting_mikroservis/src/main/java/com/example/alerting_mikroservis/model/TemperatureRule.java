package com.example.alerting_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.*;

@Entity
@DiscriminatorValue("Temperature")
public class TemperatureRule extends Rule {

    @Transient
    private static final int numberOfMeasurements = 100;

    @Transient
    private static final Queue<Temperature> recentMeasurements = new LinkedList<>();

    @Transient
    private int counter;

    public TemperatureRule(){}

    public TemperatureRule(@JsonProperty("limit") Double limit, @JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, limit, null, null, inARow);
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
