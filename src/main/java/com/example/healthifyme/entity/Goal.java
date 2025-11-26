package com.example.healthifyme.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "goals")
@AllArgsConstructor
@NoArgsConstructor
public class Goal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private GoalType goalType;
    private Double targetValue;
    private Double currentValue;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "goal")
    private List<Workout> workouts;
    @OneToMany(mappedBy = "goal")
    private List<Meal> meals;

    public enum GoalType {
        BREAKFAST, LUNCH, SNACK, DINNER
    }
}
