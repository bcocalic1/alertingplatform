package com.example.alerting_mikroservis.microservice_classes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {
    UUID userId;
    String email;
    String password;

    public User(@JsonProperty("email")String email, @JsonProperty("password")String password) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
