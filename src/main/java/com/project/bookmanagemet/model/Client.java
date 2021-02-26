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
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private Long clientId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private Date reservationDate;

    @OneToMany(mappedBy = "client", fetch= FetchType.LAZY)
    private Set<Book> books;

}
