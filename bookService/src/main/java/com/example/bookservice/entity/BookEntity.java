package com.example.bookservice.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Book")
public class BookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String title;

  @Column(nullable = false, length = 100)
  private String author;

  @Column(nullable = false, unique = true, length = 20, insertable = false, updatable = false)
  private String isbn;

  @Column(nullable = false)
  private Double price;

  @Column(name = "published_date")
  private LocalDate publishedDate;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
}

