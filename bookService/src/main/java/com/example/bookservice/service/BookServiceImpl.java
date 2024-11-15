package com.example.bookservice.service;

import com.example.bookservice.entity.BookEntity;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Page<BookEntity> getAllBooks(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    Page<BookEntity> books = bookRepository.findAll(pageable);
    if (books.isEmpty()) {
      throw new RuntimeException("No books found");
    }
    return books;
  }

  @Override
  public Optional<BookEntity> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  @Override
  public BookEntity UpsertBook(BookEntity book) {
    return bookRepository.save(book);
  }

  @Override
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

  @Override
  public long countBooks() {
    return bookRepository.count();
  }

  @Override
  public boolean doesBookExist(Long id) {
    return bookRepository.existsById(id);
  }

  @Override
  public Page<BookEntity> getBooksByIds(List<Long> ids, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    return bookRepository.findByIds(ids, pageable);
  }

  @Override
  public void deleteBooks(List<Long> ids) {
    bookRepository.deleteAll();
  }

  @Override
  public Page<BookEntity> getBooksByAuthor(String author, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    return bookRepository.findByAuthor(author, pageable);
  }

  @Override
  public List<BookEntity> getBooksByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  @Override
  public List<BookEntity> getBooksByAuthorAndTitle(String author, String title) {
    return bookRepository.findByAuthorAndTitle(author, title);
  }

  @Override
  public Page<BookEntity> getBooksByAuthorOrTitle(String author, String title, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
    return bookRepository.findByAuthorOrTitle(author, title, pageable);
  }

  @Override
  public BookEntity updateBook(BookEntity book) {
    return bookRepository.updateTitleById(book.getId(), book.getTitle());
  }
}
