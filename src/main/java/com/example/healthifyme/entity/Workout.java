package com.example.healthifyme.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "goal_id")
    private Goal goal;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String name; // e.g., "Morning Cardio", "Leg Day"

    @OneToMany(mappedBy = "workout")
    private List<ActivityLog> activities = new ArrayList<>();

    public Long getDurationInMinutes() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime, endTime).toMinutes();
        }
        return 0L;
    }
}
