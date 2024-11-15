package com.example.bookservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  @KafkaListener(topics = "book-events", groupId = "book-service")
  public void consume(String message) {
    System.out.println("Consumed message: " + message);
  }
}
