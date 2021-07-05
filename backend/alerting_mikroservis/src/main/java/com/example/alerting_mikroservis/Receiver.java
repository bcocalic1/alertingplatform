package com.example.alerting_mikroservis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "user-event")
    public void receiveUserMessage(String cs) {
        System.out.println("Received <<<<<  " + cs.toString() + ">>>>>");
    }

    @RabbitListener(queues = "cpu-measurement")
    public void receiveCpuMessage(String cs) {
        System.out.println("Received <<<<<  " + cs.toString() + ">>>>>");
    }
}
