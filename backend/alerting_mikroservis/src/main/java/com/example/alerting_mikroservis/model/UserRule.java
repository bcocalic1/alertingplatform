package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.microservice_classes.Event;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class UserRule extends Rule{

    private static final int numberOfEvents = 50;
    private Queue<Event> recentEvents;
    private int counter;

    public UserRule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, limit, timePeriod, timeUnit, inARow);
        recentEvents = new LinkedList<>();
        this.counter = 0;
    }

    @Override
    public String getDescription() {
        return "Greska pri loginu vise od " + this.getInARow() + " puta";
    }

    private void cleanUpAfterUser(UUID userId){
        this.reverseRecentEvents();
        Queue<Event> temp = this.recentEvents;
        this.recentEvents.clear();
        while(!temp.isEmpty()){
            Event event = temp.poll();
            if(!event.getUserId().equals(userId)){
                this.recentEvents.add(event);
            }
        }

    }

    private void reverseRecentEvents(){
        Queue<Event> temp = this.recentEvents;
        this.recentEvents.clear();
        while(!temp.isEmpty()){
            this.recentEvents.add(temp.poll());
        }
    }

    public boolean sendAlert(Event event){
        if(this.recentEvents.size() >= numberOfEvents){
            this.recentEvents.poll();
        }
        this.recentEvents.add(event);
        if(event.isSuccessfulLogin()) return false;
        Queue<Event> temp = this.recentEvents;
        while(!temp.isEmpty()){
            Event e = temp.poll();
            if(e.getUserId().equals(event.getUserId()) && !e.isSuccessfulLogin()){
                counter++;
            }else{
                return false;
            }
            if(counter >= this.getInARow()){
                this.cleanUpAfterUser(event.getUserId());
                counter = 0;
                return true;
            }
        }
        return false;
    }

}
