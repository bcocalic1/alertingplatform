package com.example.alerting_mikroservis;

public class Alert {
    private String name;
    private String service;
    private String severity;
    private String description;

    public Alert(String name, String service, String severity, String description) {
        this.name = name;
        this.service = service;
        this.severity = severity;
        this.description = description;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

