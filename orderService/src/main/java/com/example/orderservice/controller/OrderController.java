package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.service.OrderService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/oder-service")
public class OrderController {

  @Value("${jwt.secret}")
  private String secret;

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/order")
  public String getOrder(@RequestHeader("Authorization") String token) {
    log.info("Token: {}", token);
    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }

    Claims claims = Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
        .build()
        .parseClaimsJws(token)
        .getBody();

    String username = claims.getSubject();

    // 사용자 이름 등을 사용해 필요한 비즈니스 로직 수행
    return "Order details for user: " + username;
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderEntity> getOrderById(@PathVariable("id") Long id) {
    OrderEntity order = orderService.getOrderById(id);
    return new ResponseEntity(order, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<OrderEntity> getAllOrders() {
    List<OrderEntity> orders = orderService.getAllOrders();
    return new ResponseEntity(orders, HttpStatus.OK);
  }
}
