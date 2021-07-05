package com.example.alerting_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CPURule.class, name = "CPU"),
        @JsonSubTypes.Type(value = TemperatureRule.class, name = "Temperature"),
        @JsonSubTypes.Type(value = UserRule.class, name = "User management"),
        @JsonSubTypes.Type(value = FileRule.class, name = "File")
})
public abstract class Rule {
    private String name;
    private String service;
    private Double limit;
    private Double timePeriod;
    private String timeUnit;
    private String severity;
    private int inARow;

    public Rule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        this.name = name;
        this.service = service;
        this.limit = limit;
        this.timePeriod = timePeriod;
        this.timeUnit = timeUnit;
        this.severity = severity;
        this.inARow = inARow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }

    public Double getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Double timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getDescription(){
        return "kombinovano pravilo/toString() metoda";
    }

    public String getSeverity() {
        return severity;
    }

    public int getInARow() {
        return inARow;
    }
}
