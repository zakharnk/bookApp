package com.project.bookmanagemet.kafka;

import com.project.bookmanagemet.model.Book;
import com.project.bookmanagemet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookConsumer {
    @Autowired
private BookRepository bookRepository;


    @KafkaListener(
            topics = "book",
            groupId = "book",
            containerFactory = "bookKafkaListenerContainerFactory")
    public void bookListener(Book book) {
        bookRepository.save(book);
    }

}

