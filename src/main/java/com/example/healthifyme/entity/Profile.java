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
    private Double weight;
    private Double height;
    private String gender;
    private String activityLevel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", referencedColumnName = "id")
    private Goal goal;
}
