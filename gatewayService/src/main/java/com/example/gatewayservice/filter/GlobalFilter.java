package com.example.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

  public GlobalFilter() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    //filter에서 하고 싶은 내용을 재정의
    //pre filter 동작
    return ((exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      ServerHttpResponse response = exchange.getResponse();

      log.info("Global filter message : {}", config.getMessage());

      if (config.isPreLogger()) {
        log.info("Global filter Start : request id -> {}", request.getId());
      }
      //post filter 동작
      return chain.filter(exchange).then(Mono.fromRunnable(() -> {  //스프링5에서 지원하는 기능으로 비동기로 값을 전달할때 사용되는 객체
        if (config.isPostLogger()) {
          log.info("Global filter End : response code -> {}", response.getStatusCode());
        }

      }));
    });
  }

  @Data
  public static class Config {
    // configuration 정보 입력
    private String Message;
    private boolean preLogger;
    private boolean postLogger;
  }
}