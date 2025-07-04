package com.example.py_backend.dto;

public class AuthResponse {
    private Long id;
    private String uid;
    private String email;
    private String name;

    public AuthResponse(Long id, String uid, String email, String name) {
        this.id = id; 
        this.uid = uid; 
        this.email = email; 
        this.name = name;
    }

    //get,set
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
