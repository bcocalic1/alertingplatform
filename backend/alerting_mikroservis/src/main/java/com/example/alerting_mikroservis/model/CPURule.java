package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.CPUMeasurement;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CPURule extends Rule {
    public CPURule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit) {
        super(name, service, severity, limit, 0.0, "N/A");
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
}
