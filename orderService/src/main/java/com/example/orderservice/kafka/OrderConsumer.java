package com.example.orderservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {

  @KafkaListener(topics = "oder-events", groupId = "order-service")
  public void consume(String message) {
    log.info("Consumed message: " + message);
  }
}
