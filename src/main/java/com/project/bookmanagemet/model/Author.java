package com.project.bookmanagemet.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

        @Id
        private Long authorId;

        @NotNull
        private String name;

        @NotNull
        private String surname;

        @ManyToMany(cascade = { CascadeType.ALL })
        @JoinTable(
                name = "book_author",
                joinColumns = { @JoinColumn(name = "authorId") },
                inverseJoinColumns = { @JoinColumn(name = "bookId") }
        )
        Set<Book> books = new HashSet<>();

    }
