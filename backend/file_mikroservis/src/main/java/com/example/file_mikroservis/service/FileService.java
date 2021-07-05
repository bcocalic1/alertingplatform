package com.example.file_mikroservis.service;

import com.example.file_mikroservis.dao.FileDaoInterface;
import com.example.file_mikroservis.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private final FileDaoInterface fileDaoInterface;

    @Autowired
    public FileService(@Qualifier("logs") FileDaoInterface fileDaoInterface) {
        this.fileDaoInterface = fileDaoInterface;
    }

    public void addLog(File log){
        fileDaoInterface.addLog(log);
    }

    public List<File> getLogs() {
        return fileDaoInterface.getLogs();
    }
}
