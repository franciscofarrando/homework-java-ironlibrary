package com.example.ironlibrary.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "usn")
    private String usn;
    @Column(name = "name")
    private String name;

    public Student() {}
    public Student(String usn, String name) {
        this.usn = usn;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", usn='" + usn + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
