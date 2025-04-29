package com.example.ironlibrary.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String usn;
    private String name;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL,orphanRemoval = true)
    List<Issue> issues;
    public Student() {
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
