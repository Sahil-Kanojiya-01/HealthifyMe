package com.example.healthifyme.controller;

import com.example.healthifyme.DTO.request.UpdateProfileRequest;
import com.example.healthifyme.DTO.response.ProfileResponse;
import com.example.healthifyme.DTO.response.RestApiResponse;
import com.example.healthifyme.security.UserDetailsImpl;
import com.example.healthifyme.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/self")
    public ResponseEntity<RestApiResponse<ProfileResponse>> getOwnProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProfileResponse profileResponse = profileService.getProfileByUserId(userDetails.getId());
        RestApiResponse<ProfileResponse> restApiResponse =
                RestApiResponse.success("Profile fetched successfully", profileResponse, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

    @PutMapping("/self")
    public ResponseEntity<RestApiResponse<Void>> updateOwnProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                  @Valid @RequestBody UpdateProfileRequest updateProfileRequest) {
        profileService.updateOwnProfile(userDetails.getId(), updateProfileRequest);
        RestApiResponse<Void> restApiResponse =
                RestApiResponse.success("Profile updated successfully", null, HttpStatus.OK);
        return restApiResponse.toResponseEntity();
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<RestApiResponse<Void>> deleteProfile(@PathVariable("id") UUID id) {
//        profileService.deleteProfile(id);
//        RestApiResponse<Void> rest =
//                RestApiResponse.success("Profile deleted successfully", null, HttpStatus.NO_CONTENT);
//        return rest.toResponseEntity();
//    }
}
