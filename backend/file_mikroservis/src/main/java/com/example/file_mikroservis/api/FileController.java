package com.example.file_mikroservis.api;

import com.example.file_mikroservis.model.File;
import com.example.file_mikroservis.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(@RequestBody FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public void addLog(@RequestBody File log) throws IOException, TimeoutException, JSONException {
        fileService.addLog(log);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(log.getLogs());
        obj.put("log", jsonArray);
        channel.basicPublish("", "file-log", null, obj.toString().getBytes());
        channel.close();
        connection.close();
    }
}
