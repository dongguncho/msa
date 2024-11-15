package com.example.orderservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondController {
  Environment env;

  public SecondController(Environment env) {
    this.env = env;
  }

  @GetMapping("/welcome")
  public String welcome() {
    return "Welcome Second  Service";
  }

  @GetMapping("/message")
  public String message(@RequestHeader("second-request") String header) {
    log.info(header);
    return "Hello Second  Service";
  }

  @GetMapping("/check")
  public String check() {
    return "Check Second Service";
  }
}
