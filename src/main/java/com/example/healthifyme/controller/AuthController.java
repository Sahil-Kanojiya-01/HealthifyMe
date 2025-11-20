package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.LoginUserRequest;
import com.example.healthifyme.DTO.request.RegisterUserRequest;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.DTO.response.JwtTokenResponse;
import com.example.healthifyme.security.JwtService;
import com.example.healthifyme.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthService authService;

    @Operation(
            summary = "Login user",
            description = "Authenticates the user with email and password. Returns a JWT token on successful login.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User authenticated successfully",
                            content = @Content(schema = @Schema(implementation = JwtTokenResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input — validation failed"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Authentication failed — incorrect email or password"
                    ),
            }
    )
    @PostMapping("/login")
    public ResponseEntity<RestApiResponse<JwtTokenResponse>> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getEmail(), loginUserRequest.getPassword()));

        String token = jwtService.generateToken(loginUserRequest.getEmail());
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(token);

        log.info("User logged in successfully: {}", loginUserRequest.getEmail());

        RestApiResponse<JwtTokenResponse> restApiResponse = RestApiResponse.success("User logged in successfully", jwtTokenResponse, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates a user and returns a JWT token",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User registered successfully",
                            content = @Content(schema = @Schema(implementation = JwtTokenResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input — validation failed"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "User already exists with the provided email"
                    ),
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RestApiResponse<Void>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        authService.register(registerUserRequest.getEmail(), registerUserRequest.getPassword());

        RestApiResponse<Void> restApiResponse = RestApiResponse.success("User registered successfully", null, HttpStatus.CREATED);
        return restApiResponse.toResponseEntity();
    }
}
