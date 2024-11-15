package com.example.bookservice.controller;

import com.example.bookservice.entity.BookEntity;
import com.example.bookservice.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/book-service")
public class bookController {

  private final BookService bookService;

  public bookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/all")
  public ResponseEntity<Page<BookEntity>> all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Page<BookEntity> books = bookService.getAllBooks(page, size);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @GetMapping("/book")
  public ResponseEntity<BookEntity> book(@PathVariable("book_id") Long bookId) {
    Optional<BookEntity> book = bookService.getBookById(bookId);
    if (book == null) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(book, HttpStatus.OK);
    }
  }

  @PostMapping("/upsert")
  public ResponseEntity<BookEntity> upsert(@RequestBody BookEntity book) {
    BookEntity upsertedBook = bookService.UpsertBook(book);
    return new ResponseEntity(upsertedBook, HttpStatus.OK);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Void> delete(@PathVariable("book_id") Long bookId) {
    bookService.deleteBook(bookId);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/count")
  public ResponseEntity<Long> count() {
    long count = bookService.countBooks();
    return new ResponseEntity(count, HttpStatus.OK);
  }

  @GetMapping("/exists")
  public ResponseEntity<Boolean> exists(@PathVariable("book_id") Long bookId) {
    boolean exists = bookService.doesBookExist(bookId);
    return new ResponseEntity(exists, HttpStatus.OK);
  }

  @GetMapping("/books")
  public ResponseEntity<BookEntity> books(@RequestBody List<Long> ids, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Page<BookEntity> books = bookService.getBooksByIds(ids, page, size);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @DeleteMapping("/delete-books")
  public ResponseEntity<Void> deleteBooks(@RequestBody List<Long> ids) {
    bookService.deleteBooks(ids);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/author")
  public ResponseEntity<BookEntity> booksByAuthor(
      @PathVariable("author") String author,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<BookEntity> books = bookService.getBooksByAuthor(author, page, size);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @GetMapping("/title")
  public ResponseEntity<BookEntity> booksByTitle(@PathVariable("title") String title) {
    List<BookEntity> books = bookService.getBooksByTitle(title);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @GetMapping("/author-title")
  public ResponseEntity<BookEntity> booksByAuthorAndTitle(@PathVariable("author") String author, @PathVariable("title") String title) {
    List<BookEntity> books = bookService.getBooksByAuthorAndTitle(author, title);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @GetMapping("/serach")
  public ResponseEntity<BookEntity> booksByAuthorOrTitle(
      @PathVariable("author") String author,
      @PathVariable("title") String title,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<BookEntity> books = bookService.getBooksByAuthorOrTitle(author, title, page, size);
    if (books.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity(books, HttpStatus.OK);
    }
  }

  @PostMapping("/update")
  public ResponseEntity<BookEntity> update(@RequestBody BookEntity book) {
    BookEntity updatedBook = bookService.updateBook(book);
    return new ResponseEntity(updatedBook, HttpStatus.OK);
  }
}
