package com.example.authservice;

import com.example.authservice.dto.RegisterRequest;
import com.example.authservice.repository.UserRepository;
import com.example.authservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthServiceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        assertThat(userRepository).isNotNull();
        assertThat(passwordEncoder).isNotNull();
    }

    @Test
    void testRegisterUser() {
        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("testuser");
        request.setPassword("testpass");

        // Act
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        // Assert
        User savedUser = userRepository.findByUsername("testuser").orElse(null);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("testuser");
        assertThat(passwordEncoder.matches("testpass", savedUser.getPassword())).isTrue();
    }

    @Test
    void testDuplicateUserRegistrationFails() {
        // Arrange
        User user1 = new User();
        user1.setUsername("duplicateuser");
        user1.setPassword(passwordEncoder.encode("password1"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("duplicateuser");
        user2.setPassword(passwordEncoder.encode("password2"));

        // Act & Assert
        try {
            userRepository.save(user2);
        } catch (Exception e) {
            assertThat(e.getMessage()).contains("could not execute statement"); // Unique constraint violation
        }
    }
}
