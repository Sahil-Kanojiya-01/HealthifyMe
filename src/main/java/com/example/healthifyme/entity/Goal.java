package com.example.healthifyme.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "goals")
@AllArgsConstructor
@NoArgsConstructor
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    private Double targetValue;

    private Double currentValue;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public enum GoalType {
        WEIGHT_LOSS, STEPS_PER_DAY, CALORIES, NOT_SET
    }
}
