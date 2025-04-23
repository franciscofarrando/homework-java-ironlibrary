package com.example.ironlibrary.models;

import jakarta.persistence.*;
import com.example.ironlibrary.models.Book;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;


    public Author() {
    }

    public Author(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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


    @Override
    public String toString() {
        return "Author{" +
                "authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
