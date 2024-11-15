package com.example.memberservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
  private String grantType;
//  private String refreshToken;
  private String accessToken;

}
