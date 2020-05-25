package com.naresh.firstservice.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

//@Entity
//@JsonIgnoreProperties(value = {"dob", "id"})
public class User {
//    @Id
//    @Column(name="id")
    private int id;

    @NotNull(message = "Name cannot be null")
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;

    @Past
    @JsonIgnore
    private Date dob;
    private static int counter = 3;

    protected User(){

    }
    public User(String name, Date dob) {
        super();
        this.name = name;
        this.dob = dob;
        this.id = 0;
    }

    public User(int id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
