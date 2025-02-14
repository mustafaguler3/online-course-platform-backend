package com.example.course.controller;

import com.example.course.dto.LoginDto;
import com.example.course.dto.TokenDto;
import com.example.course.dto.UserDto;
import com.example.course.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        authService.register(userDto);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        TokenDto loginValue = authService.login(loginDto);

        return ResponseEntity.ok(loginValue);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String token){
        boolean isVerified = authService.verifyUser(token);
        if (isVerified){
            return ResponseEntity.ok("User verified successfully");
        }else{
            return ResponseEntity.badRequest().body("Verification token is invalid or expired");
        }
    }
}
