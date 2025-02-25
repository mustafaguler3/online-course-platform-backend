package com.example.course.service.impl;

import com.example.course.domain.Role;
import com.example.course.domain.User;
import com.example.course.domain.VerificationToken;
import com.example.course.dto.LoginDto;
import com.example.course.dto.TokenDto;
import com.example.course.dto.UserDto;
import com.example.course.enums.UserRoles;
import com.example.course.exceptions.UserDisabledException;
import com.example.course.exceptions.UserNotFoundException;
import com.example.course.repository.RoleRepository;
import com.example.course.repository.UserRepository;
import com.example.course.repository.VerificationTokenRepository;
import com.example.course.service.AuthService;
import com.example.course.utility.DtoConverter;
import com.example.course.utility.JwtProvider;
import com.example.course.utility.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DtoConverter dtoConverter;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public void register(UserDto userDto,MultipartFile file) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEnabled(false);

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exists.");
        }

        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(file);
            System.out.println("Image URL: " + imageUrl);
            user.setProfileImageUrl(imageUrl);
        } else {
            System.out.println("No file uploaded");
            user.setProfileImageUrl("https://res.cloudinary.com/patika-dev/image/upload/default_profile.png");
        }

        Role role = new Role();
        role.setRoleName(UserRoles.STUDENT);
        roleRepository.save(role);
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExipryDate(new Date());

        verificationTokenRepository.save(verificationToken);
        dtoConverter.toUserDto(user);
    }

    @Override
    public TokenDto login(LoginDto loginDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );
        } catch (DisabledException ex) {
            throw new RuntimeException("Verify your email address before login");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails =
                (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isEnabled()){
            throw new UserDisabledException("verify your email address before login");
        }

        if (userRepository.findByEmail(loginDto.getEmail()).isEmpty()){
            throw new UserNotFoundException("User not found with this email address");
        }

        String accessToken = jwtProvider.generateToken(userDetails);
        String refreshToken = jwtProvider.generateRefreshToken(userDetails);

        return new TokenDto(accessToken, refreshToken);
    }

    @Override
    public boolean verifyUser(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken.getToken().isEmpty()){
            throw new RuntimeException("Invalid token");
        }
        User user = verificationToken.getUser();
        verificationToken.setUser(user);
        user.setEnabled(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);

        return true;
    }
}
