package com.example.alerting_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Table
@Entity(name = "rules")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="rule_type",
        discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CPURule.class, name = "CPU"),
        @JsonSubTypes.Type(value = UserRule.class, name = "User management")
})
public abstract class Rule {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "service")
    private String service;
    @Column(name = "threshold")
    private Double limit;
    @Column(name = "time_period")
    private Double timePeriod;
    @Column(name = "time_unit")
    private String timeUnit;
    @Column(name = "severity")
    private String severity;
    @Column(name = "in_a_row")
    private int inARow;

    public Rule(){}
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getLimit() {
        return limit;
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

    public Double getTimePeriod() {
        return timePeriod;
    }

    public String getTimeUnit() {
        return timeUnit;
    }
}
