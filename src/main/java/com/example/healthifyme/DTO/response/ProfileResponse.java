package com.example.healthifyme.DTO.response;

import com.example.healthifyme.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse{
    private String id;
    private String userId;
    private Integer age;
    private Double weight;
    private Double height;
    private String gender;
    private Profile.ActivityLevel activityLevel;
    private String goalId;
    private Double bmi;
}
