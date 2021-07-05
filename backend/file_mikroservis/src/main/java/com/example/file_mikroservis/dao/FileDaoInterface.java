package com.example.file_mikroservis.dao;

import com.example.file_mikroservis.model.File;

import java.util.List;

public interface FileDaoInterface {

    void addLog(File log);
    default void addLog(){
        addLog(new File());
    }
    List<File> getLogs();
}
