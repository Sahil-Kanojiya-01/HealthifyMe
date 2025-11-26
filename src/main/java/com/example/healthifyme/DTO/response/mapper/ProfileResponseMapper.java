package com.example.healthifyme.DTO.response.mapper;

import com.example.healthifyme.DTO.response.ProfileResponse;
import com.example.healthifyme.entity.Profile;

public class ProfileResponseMapper{
    public static ProfileResponse mapFromProfile(Profile profile) {
        ProfileResponse profileResponse = new ProfileResponse();

        profileResponse.setId(profileResponse.getId());
        profileResponse.setUserId(profile.getUser().getId().toString());
        profileResponse.setAge(profileResponse.getAge());
        profileResponse.setWeight(profileResponse.getWeight());
        profileResponse.setHeight(profileResponse.getHeight());
        profileResponse.setGender(profileResponse.getGender());
        profileResponse.setActivityLevel(profile.getActivityLevel());
        profileResponse.setGoalId(profile.getGoal().getId().toString());
        profileResponse.setBmi(profile.calculateBMI());

        return profileResponse;
    }
}
