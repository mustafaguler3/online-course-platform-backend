package com.example.course.service;

import com.example.course.domain.User;
import com.example.course.dto.UserDto;

public interface UserService {
    UserDto findByEmail(String email);
    UserDto findByFirstName(String firstName);
    UserDto findByFirstNameAndLastName(String firstName, String lastName);
}
