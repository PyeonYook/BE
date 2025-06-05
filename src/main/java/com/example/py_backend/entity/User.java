package com.example.py_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uid; //파이어베이스 uid

    @Column(unique = true)
    private String email;    

    private String name;     

    //get,set
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name;}
    public void setName(String name) {this.name = name;}
}