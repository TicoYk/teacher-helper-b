package com.github.ticoyk.teacherhelperb.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @ManyToOne
    @JoinColumn(name="application_user_id", referencedColumnName="id", nullable = false)
    @JsonIgnoreProperties({"name", "email"})
    private ApplicationUser applicationUser;
    
    public Room() { }

    public Room(String name, Long columns, Long rows, List<Desk> desks, ApplicationUser applicationUser) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.desks = desks;
        this.applicationUser = applicationUser;
    }

    public Room(String name, Long columns, Long rows, ApplicationUser applicationUser) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
        this.applicationUser = applicationUser;
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

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
    
}