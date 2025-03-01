package com.example.course.service;

import com.example.course.dto.LoginDto;
import com.example.course.dto.TokenDto;
import com.example.course.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    void register(UserDto userDto,MultipartFile multipartFile);
    TokenDto login(LoginDto loginDto);
    TokenDto refreshToken(String refreshToken);
    boolean verifyUser(String token);
}
