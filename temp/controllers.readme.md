# Controllers Documentation

This document provides a comprehensive overview of all REST controllers and their endpoints in the HealthifyMe application.

---

## AuthController

**Package:** `com.example.healthifyme.controller`

**Base Path:** `/api/v1/auth`

**Description:** Handles authentication-related HTTP requests including user login and registration.

**Dependencies:**
- `AuthenticationManager` - Spring Security authentication manager
- `JwtService` - JWT token generation and validation
- `AuthService` - Business logic for authentication

### Endpoints

#### POST `/api/v1/auth/login`

**Method Declaration:**
```java
public ResponseEntity<RestApiResponse<JwtTokenResponse>> login(@Valid @RequestBody LoginUserRequest loginUserRequest)
```

**HTTP Method:** `POST`

**Request Body:** `LoginUserRequest` (JSON)
- Requires valid email and password

**Response Type:** `ResponseEntity<RestApiResponse<JwtTokenResponse>>`

**Success Response:**
- **Status Code:** `201 CREATED`
- **Body:** 
  ```json
  {
    "success": true,
    "message": "User logged in successfully",
    "status": 201,
    "data": {
      "token": "jwt-token-string"
    },
    "timestamp": "2025-11-25T11:00:49Z"
  }
  ```

**Description:** Authenticates a user with email and password, generates a JWT token upon successful authentication.

**Validation:** Uses `@Valid` annotation for request validation

**Logging:** INFO: "User logged in successfully: {email}"

---

#### POST `/api/v1/auth/register`

**Method Declaration:**
```java
public ResponseEntity<RestApiResponse<Void>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest)
```

**HTTP Method:** `POST`

**Request Body:** `RegisterUserRequest` (JSON)
- Requires valid email and password

**Response Type:** `ResponseEntity<RestApiResponse<Void>>`

**Success Response:**
- **Status Code:** `201 CREATED`
- **Body:**
  ```json
  {
    "success": true,
    "message": "User registered successfully",
    "status": 201,
    "data": null,
    "timestamp": "2025-11-25T11:00:49Z"
  }
  ```

**Error Response:**
- **Status Code:** `400 BAD REQUEST` (if user already exists)
- **Exception:** `UserAlreadyExistsException`

**Description:** Registers a new user in the system with email and password.

**Validation:** Uses `@Valid` annotation for request validation

---

## Future Controllers

Based on the entity analysis, the following controllers are expected to be implemented:

### ProfileController
**Base Path:** `/api/v1/profiles`
- `GET /api/v1/profiles/{id}` - Get profile by ID
- `GET /api/v1/profiles/user/{userId}` - Get profile by user ID
- `POST /api/v1/profiles` - Create new profile
- `PUT /api/v1/profiles/{id}` - Update profile
- `DELETE /api/v1/profiles/{id}` - Delete profile
- `GET /api/v1/profiles/{id}/bmi` - Calculate and return BMI

### GoalController
**Base Path:** `/api/v1/goals`
- `GET /api/v1/goals/{id}` - Get goal by ID
- `GET /api/v1/goals/profile/{profileId}` - Get goals by profile
- `POST /api/v1/goals` - Create new goal
- `PUT /api/v1/goals/{id}` - Update goal
- `DELETE /api/v1/goals/{id}` - Delete goal
- `PATCH /api/v1/goals/{id}/progress` - Update goal progress

### WorkoutController
**Base Path:** `/api/v1/workouts`
- `GET /api/v1/workouts/{id}` - Get workout by ID
- `GET /api/v1/workouts/user/{userId}` - Get workouts by user
- `POST /api/v1/workouts` - Create new workout
- `PUT /api/v1/workouts/{id}` - Update workout
- `DELETE /api/v1/workouts/{id}` - Delete workout
- `PATCH /api/v1/workouts/{id}/complete` - Mark workout as complete
- `GET /api/v1/workouts/{id}/duration` - Get workout duration

### ActivityLogController
**Base Path:** `/api/v1/activity-logs`
- `GET /api/v1/activity-logs/{id}` - Get activity log by ID
- `GET /api/v1/activity-logs/workout/{workoutId}` - Get activity logs by workout
- `POST /api/v1/activity-logs` - Create new activity log
- `PUT /api/v1/activity-logs/{id}` - Update activity log
- `DELETE /api/v1/activity-logs/{id}` - Delete activity log

### ExerciseController
**Base Path:** `/api/v1/exercises`
- `GET /api/v1/exercises` - Get all exercises
- `GET /api/v1/exercises/{id}` - Get exercise by ID
- `GET /api/v1/exercises/muscle-group/{muscleGroup}` - Filter by muscle group
- `GET /api/v1/exercises/equipment/{equipment}` - Filter by equipment
- `POST /api/v1/exercises` - Create new exercise
- `PUT /api/v1/exercises/{id}` - Update exercise
- `DELETE /api/v1/exercises/{id}` - Delete exercise

### MealController
**Base Path:** `/api/v1/meals`
- `GET /api/v1/meals/{id}` - Get meal by ID
- `GET /api/v1/meals/user/{userId}` - Get meals by user
- `GET /api/v1/meals/user/{userId}/date/{date}` - Get meals by user and date
- `GET /api/v1/meals/type/{type}` - Get meals by type
- `POST /api/v1/meals` - Create new meal
- `PUT /api/v1/meals/{id}` - Update meal
- `DELETE /api/v1/meals/{id}` - Delete meal
- `GET /api/v1/meals/{id}/calories` - Get total calories for meal

### MealItemController
**Base Path:** `/api/v1/meal-items`
- `GET /api/v1/meal-items/{id}` - Get meal item by ID
- `GET /api/v1/meal-items/meal/{mealId}` - Get meal items by meal
- `POST /api/v1/meal-items` - Add food item to meal
- `PUT /api/v1/meal-items/{id}` - Update meal item
- `DELETE /api/v1/meal-items/{id}` - Delete meal item

### FoodController
**Base Path:** `/api/v1/foods`
- `GET /api/v1/foods` - Get all foods
- `GET /api/v1/foods/{id}` - Get food by ID
- `GET /api/v1/foods/search?name={name}` - Search foods by name
- `POST /api/v1/foods` - Create new food
- `PUT /api/v1/foods/{id}` - Update food
- `DELETE /api/v1/foods/{id}` - Delete food