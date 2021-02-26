package com.project.bookmanagemet.controller;

import com.project.bookmanagemet.model.Book;
import com.project.bookmanagemet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private KafkaTemplate<String, Book> kafkaTemplate;
    private BookService bookService;

    @Autowired
    public BookController(KafkaTemplate<String, Book> kafkaTemplate, BookService bookService) {
        this.kafkaTemplate = kafkaTemplate;
        this.bookService = bookService;
    }

    @PostMapping("/book/create")
    public ResponseEntity<?> update(@RequestBody Book book) {
        kafkaTemplate.send("book", book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/book")
    public ResponseEntity<Book> getByTitle(String title) {
        Book book = bookService.getBookByTitle(title);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @GetMapping("/bookByCategory")
    public ResponseEntity<List<Book>> getByCategory(String category) {
        List<Book> book = bookService.getBookByCategory(category);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @GetMapping("/bookByAuthor")
    public ResponseEntity<List<Book>> getByAuthor(String author) {
        List<Book> book = bookService.getBookByAuthors(author);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> book = bookService.getAllBooks();
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @GetMapping("/allAvailableBooks")
    public ResponseEntity<List<Book>> getAllAvailable() {
        List<Book> book = bookService.getAllAvailableBooks();
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @PostMapping("/reserve/{bookId}/client/{clientId}")
    public ResponseEntity<?> reserve(@PathVariable("bookId") Long bookId, @PathVariable("clientId") Long clientId) {
        String message = bookService.reserveBook(bookId, clientId);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/unReserve/{bookId}}")
    public ResponseEntity<?> unReserve(@PathVariable("bookId") Long bookId) {
        bookService.unReserveBook(bookId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}






