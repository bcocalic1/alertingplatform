package com.example.alerting_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.*;

@Entity
@DiscriminatorValue("cpu")
public class CPURule extends Rule {
    @Transient
    private static final int numberOfMeasurements = 50;

    @Transient
    private static final Queue<CPUMeasurement> recentMeasurements = new LinkedList<>();

    @Transient
    private static int counter = 0;

    public CPURule(){}

    public CPURule(@JsonProperty("limit") Double limit, @JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, limit, null, null, inARow);
    }

    @Override
    public String getDescription() {
        return "CPU ne moze raditi na vise od " + this.getLimit() + "%";
    }

    public boolean followsRule(CPUMeasurement measurement){
        double average = 0;
        for(Double m : measurement.getMeasurement()){
            average+=m;
        }
        average /= measurement.getMeasurement().size();
        if(average < this.getLimit()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendAlert(CPUMeasurement measurement){
        if(this.recentMeasurements.size() >= numberOfMeasurements){
            this.recentMeasurements.poll();
        }
        this.recentMeasurements.add(measurement);

        Queue<CPUMeasurement> temp = this.recentMeasurements;
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
