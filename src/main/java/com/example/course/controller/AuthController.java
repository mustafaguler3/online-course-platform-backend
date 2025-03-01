package com.example.course.controller;

import com.example.course.dto.LoginDto;
import com.example.course.dto.RefreshTokenDto;
import com.example.course.dto.TokenDto;
import com.example.course.dto.UserDto;
import com.example.course.service.AuthService;
import com.example.course.utility.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@ModelAttribute UserDto userDto,
                                      @RequestPart(value = "file", required = false) MultipartFile file){
        authService.register(userDto,file);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        TokenDto loginValue = authService.login(loginDto);

        return ResponseEntity.ok(loginValue);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto> refresh(@RequestBody RefreshTokenDto refreshTokenDto) {
        TokenDto tokenDto = authService.refreshToken(refreshTokenDto.getRefreshToken());
        return ResponseEntity.ok(tokenDto);
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
