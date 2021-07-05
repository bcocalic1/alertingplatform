package com.example.alerting_mikroservis.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.*;

@Entity
@DiscriminatorValue("users")
public class UserRule extends Rule{


    @Transient
    private static final int numberOfEvents = 50;


    @Transient
    private static final Queue<Event> recentEvents = new LinkedList<>();;


    @Transient
    private static int counter = 0;

    public UserRule(){}

    public UserRule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, null, null, null, inARow);
    }

    @Override
    public String getDescription() {
        return "Greska pri loginu vise od " + this.getInARow() + " puta";
    }

    private void cleanUpAfterUser(UUID userId){
        this.reverseRecentEvents();
        Queue<Event> temp = new LinkedList<>(this.recentEvents);
        this.recentEvents.clear();
        while(!temp.isEmpty()){
            Event event = temp.poll();
            if(!event.getUserId().equals(userId)){
                this.recentEvents.add(event);
            }
        }

    }

    private void reverseRecentEvents(){
        Queue<Event> temp = new LinkedList<>(this.recentEvents);
        this.recentEvents.clear();
        while(!temp.isEmpty()){
            this.recentEvents.add(temp.poll());
        }
    }

    public boolean sendAlert(Event event){
        this.recentEvents.add(event);
        if(this.recentEvents.size() >= numberOfEvents){
            this.recentEvents.poll();
        }

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
