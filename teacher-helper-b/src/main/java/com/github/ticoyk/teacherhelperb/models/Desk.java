package com.github.ticoyk.teacherhelperb.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Desk {
    
    private Integer posX;
    private Integer posY;

    @OneToOne
    private Student student;

    public Desk() { }

    public Desk(Integer posX, Integer posY, Student student) {
        this.posX = posX;
        this.posY = posY;
        this.student = student;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
}
