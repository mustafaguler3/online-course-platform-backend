package com.example.course.service;

import com.example.course.dto.LoginDto;
import com.example.course.dto.TokenDto;
import com.example.course.dto.UserDto;

public interface AuthService {
    void register(UserDto userDto);
    TokenDto login(LoginDto loginDto);
    boolean verifyUser(String token);
}
