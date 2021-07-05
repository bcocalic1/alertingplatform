package com.example.file_mikroservis.dao;
import com.example.file_mikroservis.model.File;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("logs")
public class FileDao implements com.example.file_mikroservis.dao.FileDaoInterface {

    private List<File> fileLogs;

    public FileDao() {
        this.fileLogs = new ArrayList<>();
    }

    @Override
    public void addLog(File log) {
        fileLogs.add(log);
    }

    @Override
    public List<File> getLogs() {
        return fileLogs;
    }

}
