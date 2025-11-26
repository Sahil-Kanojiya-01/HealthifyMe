package com.example.healthifyme.service;

import com.example.healthifyme.DTO.response.mapper.ProfileResponseMapper;
import com.example.healthifyme.DTO.request.CreateProfileRequest;
import com.example.healthifyme.DTO.request.UpdateProfileRequest;
import com.example.healthifyme.DTO.response.ProfileResponse;
import com.example.healthifyme.entity.Goal;
import com.example.healthifyme.entity.Profile;
import com.example.healthifyme.entity.User;
import com.example.healthifyme.exception.EntityNotFoundException;
import com.example.healthifyme.repository.GoalRepository;
import com.example.healthifyme.repository.ProfileRepository;
import com.example.healthifyme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileService{
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final GoalRepository goalRepository;

    @Transactional(readOnly = true)
    public ProfileResponse getProfileById(UUID id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(String.format("Profile not found with id(%s)", id)));
        return ProfileResponseMapper.mapFromProfile(profile);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfileByUserId(UUID userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(
                    ()->new EntityNotFoundException(String.format("Profile not found with UserId(%s)", userId)));
        return ProfileResponseMapper.mapFromProfile(profile);
    }

    @Transactional
    public void createProfile(CreateProfileRequest createProfileRequest) {
        Profile profile = new Profile();

        Optional<User> optionalUser = userRepository.findById(createProfileRequest.getUserId());
        optionalUser.ifPresent(profile::setUser);
        profile.setAge(createProfileRequest.getAge());
        profile.setWeight(createProfileRequest.getWeight());
        profile.setHeight(createProfileRequest.getHeight());
        profile.setGender(createProfileRequest.getGender());
        profile.setActivityLevel(createProfileRequest.getActivityLevel());
        Optional<Goal> optionalGoal = goalRepository.findById(createProfileRequest.getGoalId());
        optionalGoal.ifPresent(profile::setGoal);

        profileRepository.save(profile);
    }

    @Transactional
    public void updateProfile(UUID id, UpdateProfileRequest updateProfileRequest) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isPresent()) {
            Profile profile = optionalProfile.get();

            profile.setAge(updateProfileRequest.getAge());
            profile.setHeight(updateProfileRequest.getHeight());
            profile.setWeight(updateProfileRequest.getWeight());
            profile.setGender(updateProfileRequest.getGender());
            profile.setActivityLevel(updateProfileRequest.getActivityLevel());
            Optional<Goal> optionalGoal = goalRepository.findById(updateProfileRequest.getGoalId());
            optionalGoal.ifPresent(profile::setGoal);
        }
    }

    @Transactional
    public void deleteProfile(UUID id) {
        profileRepository.deleteById(id);
    }
}
