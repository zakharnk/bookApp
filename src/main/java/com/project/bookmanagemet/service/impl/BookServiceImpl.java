package com.project.bookmanagemet.service.impl;

import com.project.bookmanagemet.model.Book;
import com.project.bookmanagemet.model.Client;
import com.project.bookmanagemet.repository.BookRepository;
import com.project.bookmanagemet.repository.ClientRepository;
import com.project.bookmanagemet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {
    private final int RESERVATION_DAYS_COUNT = 5;
    private final int RESERVATION_BOOK_COUNT = 5;

    private BookRepository bookRepository;
    private ClientRepository clientRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ClientRepository clientRepository){

        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }
    @Override
    public void addNewBook(Book book) {

    }

    @Override
    public Book getBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAllAvailableBooks() {
        return bookRepository.findAllByReservationEndLessThanOrReservationEndIsNull(Date.from((Instant.now())));
    }


    @Override
    public String reserveBook(Long bookId, Long clientId) {
        Book book = bookRepository.findById(bookId).get();

        if (book.getReservationEnd().after(Date.from(Instant.now()))){
            if (book.getClient() != null) {
                book.setReservationEnd(AddDays(book.getReservationEnd()));
                bookRepository.save(book);
                return "Book was rebooking";
            }
            return "The book was booked until " + book.getReservationEnd().toString();
        }else{
            Client client = clientRepository.findById(clientId).get();
            if (client.getBooks().stream().count() > RESERVATION_BOOK_COUNT)
            {
                return "Client can't have more than 5 books";
            }
            book.setReservationEnd(AddDays(Date.from(Instant.now())));
            book.setClient(client);
            bookRepository.save(book);
            return "Book was booked";
        }
    }

    @Override
    public void unReserveBook(Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        book.setClient(null);
        book.setReservationEnd(null);
    }

    @Override
    public List<Book> getBookByCategory(String category) {
        return bookRepository.findAllByCategory_Title(category);
    }
    @Override
    public List<Book> getBookByAuthors(String authorSurname) {
        return bookRepository.findAllByAuthors_Surname(authorSurname);
    }

    private Date AddDays(Date incomeDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(incomeDate);
        cal.add(Calendar.DATE, RESERVATION_DAYS_COUNT);
        return cal.getTime();
    }
}
