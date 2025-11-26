package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.LoginAdminRequest;
import com.example.healthifyme.DTO.request.LoginUserRequest;
import com.example.healthifyme.DTO.request.RegisterUserRequest;
import com.example.healthifyme.DTO.response.JwtTokenResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.security.JwtService;
import com.example.healthifyme.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<RestApiResponse<JwtTokenResponse>> loginUser(
            @Valid @RequestBody LoginUserRequest loginUserRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));
        String token = jwtService.generateToken(loginUserRequest.getEmail());
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(token);
        log.info("User logged in successfully: {}", loginUserRequest.getEmail());
        RestApiResponse<JwtTokenResponse> restApiResponse =
            RestApiResponse.success("User logged in successfully", jwtTokenResponse, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/register")
    public ResponseEntity<RestApiResponse<Void>> registerUser(
            @Valid @RequestBody RegisterUserRequest registerUserRequest) {
        authService.register(registerUserRequest.getEmail(), registerUserRequest.getPassword());
        RestApiResponse<Void> restApiResponse =
            RestApiResponse.success("User registered successfully", null, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/admin-x-token")
    public ResponseEntity<RestApiResponse<JwtTokenResponse>> adminXToken(
            @Valid @RequestBody LoginAdminRequest loginAdminRequest) {
        authService.authenticateAdminCredentials(loginAdminRequest.getEmail(), loginAdminRequest.getPassword());
        String XToken = jwtService.generateAdminXToken(loginAdminRequest.getEmail());
        JwtTokenResponse jwtXTokenResponse = new JwtTokenResponse(XToken);
        log.info("Admin logged in successfully: {}", loginAdminRequest.getEmail());
        RestApiResponse<JwtTokenResponse> restApiResponse =
            RestApiResponse.success("Admin logged in successfully", jwtXTokenResponse, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }
}
