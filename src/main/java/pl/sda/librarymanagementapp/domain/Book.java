package pl.sda.librarymanagementapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private User user;
}
