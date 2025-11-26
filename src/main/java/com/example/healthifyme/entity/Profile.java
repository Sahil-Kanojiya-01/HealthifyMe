package com.example.healthifyme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile{
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private Integer age;
    private Double weight; // in kg
    private Double height; // in cm
    private String gender; // MALE, FEMALE, OTHER
    private String activityLevel; // SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", referencedColumnName = "id")
    private Goal goal;

    public Double calculateBMI() {
        if (weight != null && height != null && height > 0) {
            double heightInMeters = height / 100.0;
            return weight / (heightInMeters * heightInMeters);
        }
        return null;
    }
}
