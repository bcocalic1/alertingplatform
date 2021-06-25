package com.example.user_management_mikroservis.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public class Event {
    private Timestamp time;
    private UUID userId;
    private String reason;

    public Event(UUID userId, String reason) {
        this.userId = userId;
        this.reason = reason;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UUID getUserId() {
        return userId;
    }
}
