package com.example.healthifyme.service;

import com.example.healthifyme.entity.User;
import com.example.healthifyme.exception.UserAlreadyExistsException;
import com.example.healthifyme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String email, String password) {
        log.info("Attempting to register user with email: {}", email);

        boolean emailAlreadyExists = userRepository.existsByEmail(email);
        if (emailAlreadyExists) {
            throw new UserAlreadyExistsException(String.format("User with email(%s) already exists", email));
        }

        User user = new User();
        user.setEmail(email);

        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);

        userRepository.save(user);
        log.info("User registered successfully with email: {}", email);
    }
}
