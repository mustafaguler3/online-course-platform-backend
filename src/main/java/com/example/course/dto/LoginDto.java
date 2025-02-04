package com.example.course.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "Email address is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
