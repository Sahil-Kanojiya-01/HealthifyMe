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
@Table(name = "exercises")
public class Exercise{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String muscleGroup; // CHEST, BACK, LEGS, ARMS, SHOULDERS, CORE, CARDIO

    private String equipment; // DUMBBELL, BARBELL, MACHINE, BODYWEIGHT

    private String description;
}
