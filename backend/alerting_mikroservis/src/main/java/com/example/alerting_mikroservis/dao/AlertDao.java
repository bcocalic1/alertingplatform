package com.example.alerting_mikroservis.dao;

import com.example.alerting_mikroservis.model.Alert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("alerts")
public class AlertDao {
    private List<Alert> alerts;

    public AlertDao() {
        this.alerts = new ArrayList<>();
    }

    public void addAlert(Alert alert){
        alerts.add(alert);
    }

    public List<Alert> getAlerts(){
        return this.alerts;
    }

    public List<Alert> getAlertsBySeverity(String severity){
        List<Alert> alertList = new ArrayList<>();
        for(Alert alert : this.alerts){
            if(alert.getSeverity().equals(severity)){
                alertList.add(alert);
            }
        }
        return alertList;
    }

    public List<Alert> getAlertsByService(String service){
        List<Alert> alertList = new ArrayList<>();
        for(Alert alert : this.alerts){
            if(alert.getService().equals(service)){
                alertList.add(alert);
            }
        }
        return alertList;
    }
}
