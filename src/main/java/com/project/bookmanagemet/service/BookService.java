package com.project.bookmanagemet.service;

import com.project.bookmanagemet.model.Book;

import java.util.List;


public interface BookService {
    void addNewBook(Book book);

    Book getBookByTitle(String Title);

    List<Book> getAllBooks();

    List<Book> getAllAvailableBooks();

    String reserveBook(Long bookId, Long clientId);

    public void unReserveBook(Long bookId);

    public List<Book> getBookByCategory(String category);

    public List<Book> getBookByAuthors(String authorSurname);

}
