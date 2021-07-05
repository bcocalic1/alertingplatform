package com.example.alerting_mikroservis.microservice_classes;

import java.util.ArrayList;
import java.util.List;

//more than 5 in one minute

public class File {
    private final ArrayList<String> logs;

    public File() {
        this.logs = new ArrayList<>();
    }

    public File(ArrayList<String> logs) {
        this.logs = logs;
    }
    public ArrayList<String> getLogs() {
        return logs;
    }
}