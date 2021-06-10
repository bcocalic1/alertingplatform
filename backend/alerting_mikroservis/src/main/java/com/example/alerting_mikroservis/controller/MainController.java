package com.example.alerting_mikroservis.controller;

import com.example.alerting_mikroservis.CPUMeasurement;
import com.example.alerting_mikroservis.model.Alert;
import com.example.alerting_mikroservis.model.CPURule;
import com.example.alerting_mikroservis.model.Rule;
import com.example.alerting_mikroservis.service.AlertService;
import com.example.alerting_mikroservis.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    private final RuleService ruleService;
    private final AlertService alertService;

    @Autowired
    public MainController(@RequestBody RuleService ruleService, @RequestBody AlertService alertService) {
        this.ruleService = ruleService;
        this.alertService = alertService;
    }

    @GetMapping("/cpu")
    public CPUMeasurement getMeasurement(){
        RestTemplate restTemplate = new RestTemplate();
        return new CPUMeasurement(restTemplate.getForObject("http://localhost:8083/api/v1/cpu/get", CPUMeasurement.class));
    }

    @PostMapping("/cpu")
    public void getCPUMeasurement(@RequestBody CPUMeasurement measurement){
        CPURule rule = (CPURule) ruleService.getCPURule();
        if(rule.equals(null)){
            System.out.println("No rule added for CPU");
            return;
        }
        if(rule.sendAlert(measurement)){
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
