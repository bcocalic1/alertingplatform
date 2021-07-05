package com.example.file_mikroservis.api;

import com.example.file_mikroservis.model.File;
import com.example.file_mikroservis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(@RequestBody FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public void addLog(@RequestBody File log){
        fileService.addLog(log);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<File> request = new HttpEntity<>(log);
        File response = restTemplate.postForObject("http://localhost:8080/api/v1/file", request, File.class);
        System.out.println(response);
    }
}
