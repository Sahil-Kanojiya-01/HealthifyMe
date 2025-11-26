# DTOs Documentation

This document provides a comprehensive overview of all Data Transfer Objects (DTOs) including request and response objects in the HealthifyMe application.

## Future DTOs

Based on the entity analysis, the following DTOs are expected to be implemented:

### Request DTOs

#### ProfileRequest
- `userId` (UUID)
- `age` (Integer)
- `weight` (Double)
- `height` (Double)
- `gender` (String - MALE, FEMALE, OTHER)
- `activityLevel` (String - SEDENTARY, LIGHTLY_ACTIVE, MODERATELY_ACTIVE, VERY_ACTIVE)
- `goalId` (UUID)

#### GoalRequest
- `goalType` (GoalType enum)
- `targetValue` (Double)
- `currentValue` (Double)
- `startDate` (LocalDate)
- `endDate` (LocalDate)
- `profileId` (UUID)

#### WorkoutRequest
- `userId` (UUID)
- `goalId` (UUID)
- `name` (String)
- `startTime` (LocalDateTime)
- `endTime` (LocalDateTime)

#### ActivityLogRequest
- `workoutId` (UUID)
- `exerciseId` (UUID)
- `sets` (Integer)
- `reps` (Integer)
- `weight` (Double)
- `distance` (Double)
- `duration` (Integer)

#### ExerciseRequest
- `name` (String)
- `muscleGroup` (String)
- `equipment` (String)
- `description` (String)

#### MealRequest
- `userId` (UUID)
- `goalId` (UUID)
- `date` (LocalDate)
- `type` (MealType enum - BREAKFAST, LUNCH, SNACK, DINNER)

#### MealItemRequest
- `mealId` (UUID)
- `foodId` (UUID)
- `quantity` (Double)

#### FoodRequest
- `name` (String)
- `calories` (Double)
- `protein` (Double)
- `carbs` (Double)
- `fat` (Double)
- `servingUnit` (String)
- `servingSize` (Double)

### Response DTOs

#### ProfileResponse
- `id` (UUID)
- `userId` (UUID)
- `age` (Integer)
- `weight` (Double)
- `height` (Double)
- `gender` (String)
- `activityLevel` (String)
- `bmi` (Double)
- `goalId` (UUID)

#### GoalResponse
- `id` (UUID)
- `goalType` (GoalType)
- `targetValue` (Double)
- `currentValue` (Double)
- `progress` (Double - calculated percentage)
- `startDate` (LocalDate)
- `endDate` (LocalDate)

#### WorkoutResponse
- `id` (UUID)
- `userId` (UUID)
- `goalId` (UUID)
- `name` (String)
- `startTime` (LocalDateTime)
- `endTime` (LocalDateTime)
- `durationInMinutes` (Long)
- `activityCount` (Integer)

#### ActivityLogResponse
- `id` (UUID)
- `workoutId` (UUID)
- `exerciseId` (UUID)
- `exerciseName` (String)
- `sets` (Integer)
- `reps` (Integer)
- `weight` (Double)
- `distance` (Double)
- `duration` (Integer)

#### ExerciseResponse
- `id` (UUID)
- `name` (String)
- `muscleGroup` (String)
- `equipment` (String)
- `description` (String)

#### MealResponse
- `id` (UUID)
- `userId` (UUID)
- `goalId` (UUID)
- `date` (LocalDate)
- `type` (MealType)
- `totalCalories` (Double)
- `itemCount` (Integer)

#### MealItemResponse
- `id` (UUID)
- `mealId` (UUID)
- `foodId` (UUID)
- `foodName` (String)
- `quantity` (Double)
- `calories` (Double)

#### FoodResponse
- `id` (UUID)
- `name` (String)
- `calories` (Double)
- `protein` (Double)
- `carbs` (Double)
- `fat` (Double)
- `servingUnit` (String)
- `servingSize` (Double)