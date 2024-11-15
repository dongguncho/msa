package com.example.bookservice.controller;

import com.example.bookservice.kafka.KafkaProducer;
import com.example.bookservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-service")
public class KafkaController {

  @Autowired
  private KafkaProducer kafkaProducer;
  @Autowired
  private OrderProducer oderProducer;

  @PostMapping("/publish")
  public String sendMessage(@RequestBody String message) {
    kafkaProducer.sendMessage(message);
    return "Message published successfully";
  }
  @PostMapping("/oderPublish")
  public String oderSendMessage(@RequestBody String message) {
    oderProducer.oderSendMessage(message);
    return "Message published successfully";
  }

}
