package com.github.ticoyk.teacherhelperb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private String id;
    
    @NotNull(message = "Name may not be null")
    @NotEmpty(message = "Name may not be empty")
    private String name;

    public Student(){ }

    public Student(String name){
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
