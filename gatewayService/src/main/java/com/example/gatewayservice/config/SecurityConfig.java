package com.example.gatewayservice.config;

import com.example.gatewayservice.filter.JwtAuthenticationWebFilter;
import com.example.gatewayservice.filter.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
  @Autowired
  private JwtUtil jwtUtil;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http
        .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 보호 비활성화
        .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // HTTP 기본 인증 비활성화
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // 폼 로그인 비활성화
        .authorizeExchange(exchanges -> exchanges
//            .pathMatchers("/member-service/create").permitAll() // 공개 접근 허용
//            .pathMatchers("/member-service/login").permitAll() // 공개 접근 허용
//            .anyExchange().authenticated() // 나머지 요청은 인증 필요
        .pathMatchers("**").permitAll() // 공개 접근 허용
        )
        .addFilterAt(new JwtAuthenticationWebFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION); // JWT 인증 필터 추가
    return http.build();
  }
}
