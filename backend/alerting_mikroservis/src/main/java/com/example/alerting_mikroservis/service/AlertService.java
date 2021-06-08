package com.example.alerting_mikroservis.service;

import com.example.alerting_mikroservis.dao.AlertDao;
import com.example.alerting_mikroservis.model.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    private final AlertDao alertDao;

    @Autowired
    public AlertService(@Qualifier("alerts")AlertDao alertDao) {
        this.alertDao = alertDao;
    }

    public void addAlert(Alert alert){
        this.alertDao.addAlert(alert);
    }

    public List<Alert> getAlerts(){
        return alertDao.getAlerts();
    }

    public List<Alert> getAlertsBySeverity(String severity){
        return alertDao.getAlertsBySeverity(severity);
    }

    public List<Alert> getAlertsByService(String service){
        return alertDao.getAlertsByService(service);
    }
}
