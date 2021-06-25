package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.microservice_classes.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.Queue;

public class UserRule extends Rule{

    private static final int numberOfEvents = 50;
    private Queue<Event> recentEvents;
    private int counter;

    public UserRule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("time_period") Double timePeriod, @JsonProperty("time_unit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, null, null, null, inARow);
        recentEvents = new LinkedList<>();
        this.counter = 0;
    }

    @Override
    public String getDescription() {
        return "Greska pri loginu vise od " + this.getInARow() + " puta";
    }

    private boolean followsRule(Event event){
        if(event.getReason().equals("OK")) return true;
        else return false;
    }

    public boolean sendAlert(Event event){
        if(this.recentEvents.size() >= numberOfEvents){
            this.recentEvents.poll();
        }
        this.recentEvents.add(event);
        System.out.println(event.getReason());
        if(followsRule(event)) return false;
        Queue<Event> temp = this.recentEvents;
        while(!temp.isEmpty()){
            if(temp.poll().getUserId().equals(event.getUserId()) && !followsRule(temp.poll())){
                counter++;
            }else{
                return false;
            }
            if(counter >= this.getInARow()) return true;
        }
        return false;
    }

}
