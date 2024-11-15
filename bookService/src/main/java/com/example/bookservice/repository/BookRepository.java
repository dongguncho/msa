package com.example.bookservice.repository;

import com.example.bookservice.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity, Long> {

  Page<BookEntity> findByAuthor(String author, Pageable pageable);

  List<BookEntity> findByTitle(String title);

  List<BookEntity> findByAuthorAndTitle(String author, String title);

  @Query("SELECT b FROM BookEntity b WHERE b.author = :author OR b.title = :title")
  Page<BookEntity> findByAuthorOrTitle(String author, String title, Pageable pageable);

  @Query("SELECT b FROM BookEntity b WHERE b.id IN :ids")
  Page<BookEntity> findByIds(List<Long> ids, Pageable pageable);

  @Modifying
  @Transactional
  @Query("update BookEntity b set b.title = :title where b.id = :id")
  BookEntity updateTitleById(Long id, String title);

}
