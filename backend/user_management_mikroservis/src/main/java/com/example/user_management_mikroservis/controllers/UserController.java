package com.example.user_management_mikroservis.controllers;

import com.example.user_management_mikroservis.model.Event;
import com.example.user_management_mikroservis.model.User;
import com.example.user_management_mikroservis.services.UserService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody User user) throws Exception {
        boolean successfulLogin = this.userService.login(user.getEmail(), user.getPassword());
        Event event = new Event(user.getUserId(), successfulLogin);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        JSONObject obj = new JSONObject();
        obj.put("time", event.getTime().toString());
        obj.put("userId", event.getUserId().toString());
        obj.put("successfulLogin", event.isSuccessfulLogin());
        channel.basicPublish("", "user-event", null, obj.toString().getBytes());
        channel.close();
        connection.close();

        /*RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Event> request = new HttpEntity<>(event);
        Event response = restTemplate.postForObject("http://localhost:8080/api/v1/users", request, Event.class);
        System.out.println(response);*/
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) throws Exception {
        try {
            this.userService.register(user);
        }catch (Exception exception){
            throw exception;
        }

    }

    @GetMapping
    public List<User> getAll(){
        return this.userService.getAll();
    }
}
