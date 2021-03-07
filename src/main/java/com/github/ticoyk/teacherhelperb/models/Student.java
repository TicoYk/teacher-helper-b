package com.github.ticoyk.teacherhelperb.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @NotNull(message = "Name may not be null")
    @NotEmpty(message = "Name may not be empty")
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.DETACH)
    @JsonIgnoreProperties("student")
    private Set<Desk> desks;
    
    @ManyToOne
    @JoinColumn(name="application_user_id", referencedColumnName="id", nullable = false)
    private ApplicationUser applicationUser;
    
    public Student(){ }

    public Student(Long id,String name, ApplicationUser applicationUser){
        this.id = id;
        this.name = name;
        this.applicationUser = applicationUser;
    }
    public Student(String name, ApplicationUser applicationUser){
        this.name = name;
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

    public Set<Desk> getDesks() {
        return desks;
    }

    public void setDesks(Set<Desk> desks) {
        this.desks = desks;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

}
