package com.example.alerting_mikroservis.service;

import com.example.alerting_mikroservis.dao.AlertRepository;
import com.example.alerting_mikroservis.model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    @Autowired
    public AlertService(@Qualifier("alerts") AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void addAlert(Alert alert){
        this.alertRepository.save(alert);
    }

    public List<Alert> getAlerts(){
        return this.alertRepository.findAll();
    }

    public List<Alert> getAlertsBySeverity(String severity){
        return this.alertRepository.findBySeverity(severity);
    }

    public List<Alert> getAlertsByService(String service){
        return this.alertRepository.findByService(service);
    }
}
