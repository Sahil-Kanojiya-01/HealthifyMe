package com.example.healthifyme.DTO.response;

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
    private String activityLevel;
    private String goalId;
    private Double bmi;
}
