package com.example.Thaillo.dto;


public class RegisterResponse {
    public String name;
    public String email;
    public String token;
    public RegisterResponse(String name,String email, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
    }
}
