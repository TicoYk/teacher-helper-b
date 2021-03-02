package com.github.ticoyk.teacherhelperb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Entity
@Table(name = "application_user", indexes = {
    @Index(name = "user_email_index",  columnList="email", unique = true) 
})
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = -4695390075217494840L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Login Fields
    
    private String email;
    private String password;

    // End Login Fields

    public ApplicationUser(){}

    public ApplicationUser(String email, String password) {
        this.email = email.toLowerCase();
        this.password = password;
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

    public String getEmail() {
        return email.toLowerCase();
    }
    
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ApplicationUser [email=" + email + ", id=" + id + ", name=" + name + "]";
    }

}
