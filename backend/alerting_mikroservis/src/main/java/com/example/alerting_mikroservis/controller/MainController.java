package com.example.alerting_mikroservis.controller;

import com.example.alerting_mikroservis.model.CPUMeasurement;
import com.example.alerting_mikroservis.model.Event;
import com.example.alerting_mikroservis.model.Alert;
import com.example.alerting_mikroservis.model.CPURule;
import com.example.alerting_mikroservis.model.Rule;
import com.example.alerting_mikroservis.model.UserRule;
import com.example.alerting_mikroservis.service.AlertService;
import com.example.alerting_mikroservis.service.RuleService;
import com.example.alerting_mikroservis.model.Temperature;
import com.example.alerting_mikroservis.model.TemperatureRule;
import com.example.alerting_mikroservis.model.File;
import com.example.alerting_mikroservis.model.FileRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController{
    private final RuleService ruleService;
    private final AlertService alertService;
    private ConnectionFactory connectionFactory = null;

    @Autowired
    public MainController(RuleService ruleService, AlertService alertService) {
        this.ruleService = ruleService;
        this.alertService = alertService;
    }

   /* @PostMapping("/cpu")
    public void getCPUMeasurement(@RequestBody CPUMeasurement measurement){
        CPURule rule = (CPURule) ruleService.getRuleByService("CPU");
        if(Objects.isNull(rule)){
            System.out.println("No rule added for CPU");
            return;
        }
        if(rule.sendAlert(measurement)){
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }*/

    @RabbitListener(queues = "cpu-measurement")
    public void receiveCpuMessage(String message) {
        System.out.println("Received <<<<<  " + message + ">>>>>");
        Gson gson = new GsonBuilder().create(); // Or use new GsonBuilder().create();
        CPUMeasurement cpuMeasurement= gson.fromJson(message, CPUMeasurement.class);
        System.out.println("Measurement: " + cpuMeasurement.getMeasurement());

        CPURule rule = (CPURule) ruleService.getRuleByService("CPU");
        if(Objects.isNull(rule)){
            System.out.println("No rule added for CPU");
            return;
        }

        if(rule.sendAlert(cpuMeasurement)){
            System.out.println("sending alert");
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    @RabbitListener(queues = "user-event")
    public void receiveUserMessage(String message) {
        System.out.println("Received <<<<<  " + message + ">>>>>");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // Or use new GsonBuilder().create();
        Event event = gson.fromJson(message, Event.class);
        System.out.println("Event: " + event.getUserId());

        UserRule rule = (UserRule) ruleService.getRuleByService("users");
        if(Objects.isNull(rule)){
            System.out.println("No rule added for users");
            return;
        }

        if(rule.sendAlert(event)){
            System.out.println("sending alert");
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }

    }

    @RabbitListener(queues = "temperature-measurement")
    public void receiveTemperatureMessage(String message) {
        System.out.println("Received <<<<<  " + message + ">>>>>");
        Gson gson = new GsonBuilder().create(); // Or use new GsonBuilder().create();
        Temperature temperatureMeasurement= gson.fromJson(message, Temperature.class);
        System.out.println("Measurement: " + temperatureMeasurement.getMeasurement());

        TemperatureRule rule = (TemperatureRule) ruleService.getRuleByService("Temperature");
        if(Objects.isNull(rule)){
            System.out.println("No rule added for Temperature");
            return;
        }

        if(rule.sendAlert(temperatureMeasurement)){
            System.out.println("sending alert");
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    @RabbitListener(queues = "file-log")
    public void receiveFileMessage(String message) {
        System.out.println("Received <<<<<  " + message + ">>>>>");
        Gson gson = new GsonBuilder().create(); // Or use new GsonBuilder().create();
        File fileLog = gson.fromJson(message, File.class);
        System.out.println("Log: " + fileLog.getLogs());

        FileRule rule = (FileRule) ruleService.getRuleByService("File");
        if(Objects.isNull(rule)){
            System.out.println("No rule added for File");
            return;
        }

        if(rule.sendAlert(fileLog)){
            System.out.println("sending alert");
            Alert alert = new Alert(rule.getName(), rule.getService(), rule.getSeverity(), rule.getDescription());
            alertService.addAlert(alert);
        }
    }

    /*
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
     */

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

    @PutMapping("/rules")
    public void updateRule(@RequestBody Rule rule){
        this.ruleService.updateRule(rule);
    }

}
