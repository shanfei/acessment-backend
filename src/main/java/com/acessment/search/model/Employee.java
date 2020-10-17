package com.acessment.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "employees")
public class Employee {

    @Id
    public String id;

    public Employee() {
    }

    public Employee(String name, int age, String title, Date startFrom, Date endAt) {
        this.name = name;
        this.age = age;
        this.title = title;
        this.startFrom = startFrom;
        this.endAt = endAt;
    }

    private String name;
    private int age;
    private String title;
    private Date startFrom;
    private Date endAt;
}
