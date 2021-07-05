package com.example.cpu_mikroservis.api;

import com.example.cpu_mikroservis.model.CPUMeasurement;
import com.example.cpu_mikroservis.service.MeasurementService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/v1/cpu")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(@RequestBody MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public void addMeasurement(@RequestBody CPUMeasurement measurement) throws IOException, TimeoutException, JSONException {
        measurementService.addMeasurement(measurement);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < measurement.getMeasurement().size(); i++){
            jsonArray.put(measurement.getMeasurement().get(i));
        }
        obj.put("measurement", jsonArray);
        obj.put("numberOfProcessors", measurement.getNumberOfProcessors());
        obj.put("min", measurement.getMin());
        obj.put("max", measurement.getMax());
        channel.basicPublish("", "cpu-measurement", null, obj.toString().getBytes());
        channel.close();
        connection.close();
    }
}