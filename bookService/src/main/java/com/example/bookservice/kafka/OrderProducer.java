package com.example.bookservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {


  private static final String TOPIC = "oder-events";

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void oderSendMessage(String message) {
    kafkaTemplate.send(TOPIC, message);
  }
}



