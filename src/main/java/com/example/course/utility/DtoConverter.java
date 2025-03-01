package com.example.course.utility;

import com.example.course.domain.Role;
import com.example.course.domain.User;
import com.example.course.dto.LoginDto;
import com.example.course.dto.UserDto;
import com.example.course.service.impl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {

    @Autowired
    private CloudinaryService cloudinaryService;

    public User toUserEntity(UserDto userDto){
        User user = new User();
        user.setEnabled(userDto.isEnabled());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setProfileImageUrl(userDto.getProfileImageUrl());
        return user;
    }

    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEnabled(user.isEnabled());

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());

        for (Role role: user.getRoles()){
            userDto.setRole(role.getRoleName().name());
        }

        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setProfileImageUrl(user.getProfileImageUrl());
        return userDto;
    }

    public List<User> toUserListDto(List<UserDto> userDtos){
        return userDtos.stream().map(this::toUserEntity).collect(Collectors.toList());
    }

    public List<UserDto> toUserDtoList(List<User> users){
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }

    public LoginDto toLoginDtoToUserDto(UserDto userDto){
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(userDto.getEmail());
        loginDto.setPassword(userDto.getPassword());
        return loginDto;
    }

}
