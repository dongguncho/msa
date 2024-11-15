package com.example.memberservice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Member")
public class MemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 50, unique = true)
  private String userId;
  @Column(nullable = false, length = 50)
  private String name;
  @Column(nullable = false, length = 50, unique = true)
  private String email;
  @Column(nullable = false, length = 50)
  private String password;
  @Column(nullable = false, length = 50)
  private String encryptedPw;
  @Column(nullable = false, length = 20)
  private String role;
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
}
