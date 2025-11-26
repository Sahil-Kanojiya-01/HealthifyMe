package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.CreateProfileRequest;
import com.example.healthifyme.DTO.request.UpdateProfileRequest;
import com.example.healthifyme.DTO.response.ProfileResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController{
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<ProfileResponse>> getProfile(@PathVariable("id") UUID id) {
        ProfileResponse profileResponse = profileService.getProfileById(id);
        RestApiResponse<ProfileResponse> restApiResponse =
            RestApiResponse.success("Profile fetched successfully", profileResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestApiResponse<ProfileResponse>> getProfileByUser(@PathVariable("userId") UUID userId) {
        ProfileResponse profileResponse = profileService.getProfileByUserId(userId);
        RestApiResponse<ProfileResponse> restApiResponse =
            RestApiResponse.success("Profile fetched successfully", profileResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @PostMapping("/")
    public ResponseEntity<RestApiResponse<Void>> createProfile(
            @Valid @RequestBody CreateProfileRequest createProfileRequest) {
        profileService.createProfile(createProfileRequest);
        RestApiResponse<Void> rest = RestApiResponse.success("Profile created successfully", null, HttpStatus.CREATED);
        return rest.toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> updateProfile(@PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        profileService.updateProfile(id, updateProfileRequest);
        RestApiResponse<Void> restApiResponse =
            RestApiResponse.success("Profile updated successfully", null, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<Void>> deleteProfile(@PathVariable("id") UUID id) {
        profileService.deleteProfile(id);
        RestApiResponse<Void> rest =
            RestApiResponse.success("Profile deleted successfully", null, HttpStatus.NO_CONTENT);
        return rest.toResponseEntity();
    }
}
