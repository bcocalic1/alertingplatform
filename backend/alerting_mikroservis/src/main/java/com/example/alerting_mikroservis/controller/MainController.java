package com.example.alerting_mikroservis.controller;

import com.example.alerting_mikroservis.microservice_classes.CPUMeasurement;
import com.example.alerting_mikroservis.microservice_classes.Event;
import com.example.alerting_mikroservis.microservice_classes.Temperature;
import com.example.alerting_mikroservis.microservice_classes.File;
import com.example.alerting_mikroservis.model.*;
import com.example.alerting_mikroservis.service.AlertService;
import com.example.alerting_mikroservis.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
    private final RuleService ruleService;
    private final AlertService alertService;

    @Autowired
    public MainController(@RequestBody RuleService ruleService, @RequestBody AlertService alertService) {
        this.ruleService = ruleService;
        this.alertService = alertService;
    }

    @PostMapping("/cpu")
    public void getCPUMeasurement(@RequestBody CPUMeasurement measurement){
        CPURule rule = (CPURule) ruleService.getCPURule();
        if(Objects.isNull(rule)){
            System.out.println("No rule added for CPU");
            return;
        }
        if(rule.sendAlert(measurement)){
           Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
           alertService.addAlert(alert);
        }
    }

    @PostMapping("/users")
    public void getLoginInfo(@RequestBody Event event){
        UserRule rule = (UserRule) ruleService.getUserRule();

        if(Objects.isNull(rule)){
            System.out.println("No rule added for users");
            return;
        }

        if(rule.sendAlert(event)){
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    @PostMapping("/temperature")
    public void getTemperatureMeasurement(@RequestBody Temperature measurement){
        TemperatureRule rule = (TemperatureRule) ruleService.getTemperatureRule();
        if(Objects.isNull(rule)){
            System.out.println("No rule added for Temperature");
            return;
        }
        if(rule.sendAlert(measurement)){
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    @PostMapping("/file")
    public void getFileLOgs(@RequestBody File log){
        FileRule rule = (FileRule) ruleService.getFileRule();
        if(Objects.isNull(rule)){
            System.out.println("No rule added for Files");
            return;
        }
        if(rule.sendAlert(log)){
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    @PostMapping("/rules")
    public void addRule(@RequestBody Rule rule){
        this.ruleService.addRule(rule);
    }

    @GetMapping("/rules")
    public List<Rule> getAllRules(){
        return this.ruleService.getAllRules();
    }

    @GetMapping("/alerts")
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }

    @GetMapping("/alerts/severity")
    public List<Alert> getAlertsBySeverity(@RequestBody String severity){
        return alertService.getAlertsBySeverity(severity);
    }

    @GetMapping("/alerts/service")
    public List<Alert> getAlertsByService(@RequestBody String service){
        return alertService.getAlertsByService(service);
    }

}
