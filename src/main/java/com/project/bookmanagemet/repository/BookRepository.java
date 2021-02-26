package com.project.bookmanagemet.repository;

import com.project.bookmanagemet.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByTitle(String title);
    List<Book> findAllByCategory_Title(String title);
    List<Book> findAllByAuthors_Surname(String surname);
    List<Book>  findAllByReservationEndLessThanOrReservationEndIsNull(Date reservationEnd);
}
