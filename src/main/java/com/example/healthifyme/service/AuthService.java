package com.example.healthifyme.service;

import com.example.healthifyme.config.AdminCredentialProperties;
import com.example.healthifyme.entity.Goal;
import com.example.healthifyme.entity.Profile;
import com.example.healthifyme.entity.User;
import com.example.healthifyme.exception.UserAlreadyExistsException;
import com.example.healthifyme.repository.GoalRepository;
import com.example.healthifyme.repository.ProfileRepository;
import com.example.healthifyme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final GoalRepository goalRepository;

    private final PasswordEncoder passwordEncoder;
    private final Clock clock;

    private final AdminCredentialProperties adminCredentialProperties;

    @Transactional
    public void registerUser(String email, String password) {
        log.info("Attempting to register user with email: {}", email);
        boolean emailAlreadyExists = userRepository.existsByEmail(email);
        if (emailAlreadyExists) {
            throw new UserAlreadyExistsException(String.format("User already exists with email(%s)", email));
        }
        User user = new User();
        user.setEmail(email);
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setAge(0);
        profile.setWeight(0.0D);
        profile.setHeight(0.0D);
        profile.setGender(Profile.Gender.NOT_SET);
        profile.setActivityLevel(Profile.ActivityLevel.SEDENTARY);
        profileRepository.save(profile);
        Goal goal = new Goal();
        goal.setGoalType(Goal.GoalType.NOT_SET);
        goal.setCurrentValue(0.0D);
        goal.setTargetValue(0.0D);
        goal.setStartDate(LocalDate.now(clock));
        goal.setEndDate(LocalDate.now(clock));
        goal.setProfile(profile);
        goalRepository.save(goal);
        log.info("User registered successfully with email: {}", email);
    }

    public void authenticateAdminCredentials(String email, String password) throws AuthenticationException {
        if (!Objects.equals(email, adminCredentialProperties.getEmail())
                || !Objects.equals(password, adminCredentialProperties.getPassword())) {
            throw new BadCredentialsException("Invalid admin credentials");
        }
    }
}
