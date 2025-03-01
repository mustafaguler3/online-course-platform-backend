package com.example.course.utility;

import com.example.course.domain.Category;
import com.example.course.domain.Role;
import com.example.course.domain.User;
import com.example.course.enums.UserRoles;
import com.example.course.repository.CategoryRepository;
import com.example.course.repository.RoleRepository;
import com.example.course.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

//@Component
public class SeedDataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SeedDataLoader(CategoryRepository categoryRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category("Java"),
                    new Category("Angular"),
                    new Category("C#"),
                    new Category("React"),
                    new Category("Spring Boot"),
                    new Category("Docker"),
                    new Category("Swift")
            );
            categoryRepository.saveAll(categories);
            System.out.println("Default categories loaded!");
        }
        if (userRepository.count() == 0){

            Role role = new Role();
            role.setRoleName(UserRoles.ADMIN);
            roleRepository.save(role);

            User user = new User();
            user.setEmail("admin@example.com");
            user.setFirstName("Admin");
            user.setLastName("User");
            user.setPassword(passwordEncoder.encode("123"));
            user.setUsername("admin");
            user.setFullName("Admin User");
            user.setPhoneNumber("1234567890");
            user.setEnabled(true);
            user.setBio("This is the admin user.");
            user.setProfileImageUrl("https://res.cloudinary.com/patika-dev/image/upload/v1739031274/uwbjz1yo4k04koslt0oj.jpg");
            user.setRoles(Collections.singleton(role));

            userRepository.save(user);
        }
    }
}
