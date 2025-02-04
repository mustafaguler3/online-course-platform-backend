package com.example.course.repository;

import com.example.course.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User findByFirstName(String firstName);
    User findByUsername(String username);
    User findByFirstNameAndLastName(String firstName, String lastName);
}
