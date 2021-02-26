package com.project.bookmanagemet.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private Long categoryId;

    @NotNull
    private String title;

    @OneToMany(targetEntity = Book.class, mappedBy = "category", fetch= FetchType.LAZY)
    private Set<Book> books;

    }
