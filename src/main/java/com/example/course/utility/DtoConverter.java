package com.example.course.utility;

import com.example.course.domain.User;
import com.example.course.dto.LoginDto;
import com.example.course.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public User toUserEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName() + " " + userDto.getLastName());
        return user;
    }

    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEnabled(user.isEnabled());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFirstName() + " " + user.getLastName());
        return userDto;
    }

    public LoginDto toLoginDtoToUserDto(UserDto userDto){
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(userDto.getEmail());
        loginDto.setPassword(userDto.getPassword());
        return loginDto;
    }

}
