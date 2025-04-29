package com.example.ironlibrary.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_email")
    private String email;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,orphanRemoval = true)

    List<com.Library.Library.model.Book> books;

    public Author() {
    }

    public Author(String name, String email, List<Book> books) {
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}