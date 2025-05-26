package com.example.py_backend.dto;

public class UserLoginRequestDTO {
    private String email;
    private String password;

    //Get,set
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
