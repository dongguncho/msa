package com.example.bookservice.service;

import com.example.bookservice.entity.BookEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface BookService {
  // 도서 전체 조회
  Page<BookEntity> getAllBooks(int page, int size);
  // 도서 ID로 조회
  Optional<BookEntity> getBookById(Long id);
  // 도서 추가 및 수정
  BookEntity UpsertBook(BookEntity book);
  // 도서 삭제
  void deleteBook(Long id);
  // 도서 수 조회
  long countBooks();
  // 도서 존재 여부 조회
  boolean doesBookExist(Long id);
  // 도서 ID 리스트로 조회
  Page<BookEntity> getBooksByIds(List<Long> ids, int page, int size );
  // 도서 ID 리스트로 삭제
  void deleteBooks(List<Long> ids);
  // 도서 작가로 조회
  Page<BookEntity> getBooksByAuthor(String author, int page, int size);
  // 도서 제목으로 조회
  List<BookEntity> getBooksByTitle(String title);
  // 도서 작가와 제목으로 조회
  List<BookEntity> getBooksByAuthorAndTitle(String author, String title);
  // 도서 작가 또는 제목으로 조회
  Page<BookEntity> getBooksByAuthorOrTitle(String author, String title, int page, int size);

  // 도서 업데이트
  BookEntity updateBook(BookEntity book);
}
