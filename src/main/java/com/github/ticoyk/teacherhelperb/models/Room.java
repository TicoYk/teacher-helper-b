package com.github.ticoyk.teacherhelperb.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.index.Indexed;

@Entity
public class Room {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private String id;

    @Indexed(unique=true)
    private String name;
    
    private Long columns;
    private Long rows;
    
    @OneToMany
    private Set<Desk> desks;

    public Room() { }

    public Room(String name, Long columns, Long rows, Set<Desk> desks) {
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

    public Set<Desk> getDesks() {
        return desks;
    }

    public void setDesks(Set<Desk> desks) {
        this.desks = desks;
    }
    
}
