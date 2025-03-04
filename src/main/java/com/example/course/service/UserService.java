package com.example.course.service;

import com.example.course.domain.User;
import com.example.course.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDto findByEmail(String email);
    UserDto findByFirstName(String firstName);
    UserDto findByFirstNameAndLastName(String firstName, String lastName);
    String uploadProfileImage(Long userId, MultipartFile multipartFile);
    List<UserDto> findUsers();
}
