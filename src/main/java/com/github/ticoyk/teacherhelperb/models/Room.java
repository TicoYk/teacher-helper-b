package com.github.ticoyk.teacherhelperb.models;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Room {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    
    private String name;
    
    private Long columns;
    private Long rows;
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "room_id", nullable=false)
    private List<Desk> desks;

    public Room() { }

    public Room(String name, Long columns, Long rows, List<Desk> desks) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.desks = desks;
    }

    public Room(String name, Long columns, Long rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getColumns() {
        return columns;
    }

    public void setColumns(Long columns) {
        this.columns = columns;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(List<Desk> desks) {
        this.desks = desks;
    }
    
}