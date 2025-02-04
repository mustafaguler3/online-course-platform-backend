package com.example.course.service.impl;

import com.example.course.domain.User;
import com.example.course.dto.UserDto;
import com.example.course.repository.UserRepository;
import com.example.course.service.UserService;
import com.example.course.utility.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DtoConverter dtoConverter;

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(value -> dtoConverter.toUserDto(value)).orElse(null);

    }

    @Override
    public UserDto findByFirstName(String firstName) {
        User user = userRepository.findByFirstName(firstName);

        if (user != null) {
            return dtoConverter.toUserDto(user);
        }
        return null;
    }

    @Override
    public UserDto findByFirstNameAndLastName(String firstName, String lastName) {
        return null;
    }


}
