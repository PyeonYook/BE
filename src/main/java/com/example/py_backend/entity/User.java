package com.example.py_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uid;      // Firebase UID

    @Column(unique = true)
    private String email;    // 이메일(선택 - 구글 Oauth시 제공됨)

    private String name;     // 닉네임/이름(선택)

    // Getter/Setter
    public Long getId() { return id; }
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getName() { return name;}
    public void setName(String name) {this.name = name;}
}