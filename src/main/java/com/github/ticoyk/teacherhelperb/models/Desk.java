package com.github.ticoyk.teacherhelperb.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="desk", indexes = {@Index(name = "room_posx_posy", columnList = "room_id, posx, posy", unique = true)})
public class Desk {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
        
    private Integer posX;

    private Integer posY;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="student_id", referencedColumnName="id", nullable = false)
    @JsonIgnoreProperties("desks")
    private Student student;

    public Desk() { }

    public Desk(Integer posX, Integer posY, Student student) {
        this.posX = posX;
        this.posY = posY;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
