package com.project.bookmanagemet.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

        //Unique Book Number given by company.
        @Id
        private Long id;

        //title of the book
        @NotNull
        private String title;

        @NotNull
        private Integer publishedDate;

        @NotNull
        private String description;

        private Date reservationEnd;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "categoryId")
        private Category category;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "clientId")
        private Client client;

        @ManyToMany(mappedBy = "books")
        private Set<Author> authors = new HashSet<>();

        @ManyToMany(mappedBy = "books")
        private Set<Translator> translators = new HashSet<>();

    }
