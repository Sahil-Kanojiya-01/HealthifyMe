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
@Table(name = "activity_logs")
public class ActivityLog{
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    private Integer sets;
    private Integer reps;
    private Double weight; // in kg
    private Double distance; // in km (for cardio)
    private Integer duration; // in minutes (for cardio)
}
