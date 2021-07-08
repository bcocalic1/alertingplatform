package com.example.temperatura_mikroservis.api;

import com.example.temperatura_mikroservis.model.Temperature;
import com.example.temperatura_mikroservis.service.TemperatureService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/v1/temperature")
public class TemperatureController {
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(@RequestBody TemperatureService measurementService) {
        this.temperatureService = measurementService;
    }

    @PostMapping
    public void addMeasurement(@RequestBody Temperature measurement) throws IOException, TimeoutException, JSONException {
        temperatureService.addMeasurement(measurement);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(measurement.getMeasurement());
        obj.put("measurement", jsonArray);
        obj.put("min", Temperature.getMin());
        obj.put("max", Temperature.getMax());
        channel.basicPublish("", "temperature-measurement", null, obj.toString().getBytes());
        channel.close();
        connection.close();
    }
}
