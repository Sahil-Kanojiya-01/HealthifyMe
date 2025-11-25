# DTOs Documentation

This document provides a comprehensive overview of all Data Transfer Objects (DTOs) including request and response objects in the HealthifyMe application.

---

## Request DTOs

### LoginUserRequest

**Package:** `com.example.healthifyme.DTO.request`

**Type:** Request DTO

**Purpose:** Used for user login authentication

**Fields:**

| Field | Type | Validation | Description |
|-------|------|------------|-------------|
| `email` | String | `@NotBlank`, `@Email` | User's email address (required, must be valid email format) |
| `password` | String | `@NotBlank` | User's password (required) |

**Annotations:**
- `@Data` - Lombok annotation for getters, setters, toString, equals, and hashCode
- `@NoArgsConstructor` - Generates no-argument constructor
- `@AllArgsConstructor` - Generates constructor with all arguments

**Usage:** Used in `POST /api/v1/auth/login` endpoint

**Example JSON:**
```json
{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

---

### RegisterUserRequest

**Package:** `com.example.healthifyme.DTO.request`

**Type:** Request DTO

**Purpose:** Used for new user registration

**Fields:**

| Field | Type | Validation | Description |
|-------|------|------------|-------------|
| `email` | String | `@NotBlank`, `@Email` | User's email address (required, must be valid email format) |
| `password` | String | `@NotBlank` | User's password (required) |

**Annotations:**
- `@Data` - Lombok annotation for getters, setters, toString, equals, and hashCode
- `@NoArgsConstructor` - Generates no-argument constructor
- `@AllArgsConstructor` - Generates constructor with all arguments

**Usage:** Used in `POST /api/v1/auth/register` endpoint

**Example JSON:**
```json
{
  "email": "newuser@example.com",
  "password": "securePassword123"
}
```

---

## Response DTOs

### JwtTokenResponse

**Package:** `com.example.healthifyme.DTO.response`

**Type:** Response DTO

**Purpose:** Returns JWT authentication token after successful login

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `token` | String | JWT authentication token |

**Annotations:**
- `@Data` - Lombok annotation for getters, setters, toString, equals, and hashCode
- `@NoArgsConstructor` - Generates no-argument constructor
- `@AllArgsConstructor` - Generates constructor with all arguments

**Usage:** Returned by `POST /api/v1/auth/login` endpoint

**Example JSON:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### RestApiResponse<T>

**Package:** `com.example.healthifyme.DTO.response`

**Type:** Generic Response Wrapper

**Purpose:** Standard wrapper for all successful API responses

**Generic Type:** `<T>` - The type of data being returned

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `success` | boolean | Indicates if the request was successful |
| `message` | String | Human-readable message about the response |
| `status` | int | HTTP status code |
| `data` | T | Generic data payload (can be any type) |
| `timestamp` | Instant | Timestamp when the response was created |

**Annotations:**
- `@Data` - Lombok annotation
- `@Builder` - Enables builder pattern
- `@NoArgsConstructor` - Generates no-argument constructor
- `@AllArgsConstructor` - Generates constructor with all arguments
- `@JsonInclude(JsonInclude.Include.NON_NULL)` - Only includes non-null fields in JSON

**Static Methods:**

#### `success(String message, T data, HttpStatus httpStatus): RestApiResponse<T>`
- Creates a successful response with data
- Sets `success` to `true`
- Sets timestamp to current time

#### `failure(String message, HttpStatus httpStatus): RestApiResponse<T>`
- Creates a failure response without data
- Sets `success` to `false`
- Sets timestamp to current time

#### `toResponseEntity(): ResponseEntity<RestApiResponse<T>>`
- Converts the response to a Spring `ResponseEntity`

**Example JSON (Success):**
```json
{
  "success": true,
  "message": "User logged in successfully",
  "status": 201,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  },
  "timestamp": "2025-11-25T11:00:49Z"
}
```

**Example JSON (Success with null data):**
```json
{
  "success": true,
  "message": "User registered successfully",
  "status": 201,
  "timestamp": "2025-11-25T11:00:49Z"
}
```

---

### ErrorResponse

**Package:** `com.example.healthifyme.DTO.response`

**Type:** Error Response DTO

**Purpose:** Standard wrapper for all error responses

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `success` | boolean | Always `false` for error responses |
| `message` | String | Error message describing what went wrong |
| `path` | String | API path where the error occurred |
| `status` | int | HTTP status code |
| `timestamp` | Instant | Timestamp when the error occurred |
| `validationErrors` | Map<String, String> | Field-level validation errors (optional) |

**Annotations:**
- `@Data` - Lombok annotation
- `@Builder` - Enables builder pattern
- `@NoArgsConstructor` - Generates no-argument constructor
- `@AllArgsConstructor` - Generates constructor with all arguments
- `@JsonInclude(JsonInclude.Include.NON_NULL)` - Only includes non-null fields in JSON

**Static Methods:**

#### `of(HttpStatus status, String message, String path): ErrorResponse`
- Creates an error response without validation errors
- Sets `success` to `false`
- Sets timestamp to current time

#### `of(HttpStatus status, String message, String path, List<FieldError> validationErrorsList): ErrorResponse`
- Creates an error response with path and validation errors
- Converts `FieldError` list to a map of field names to error messages

#### `of(HttpStatus status, String message, List<FieldError> validationErrorsList): ErrorResponse`
- Creates an error response with validation errors but without path
- Converts `FieldError` list to a map

#### `toResponseEntity(): ResponseEntity<ErrorResponse>`
- Converts the error response to a Spring `ResponseEntity`

#### `getValidationErrorsMap(List<FieldError> validationErrorsList): Map<String, String>`
- Helper method to convert Spring's `FieldError` list to a map
- Key: field name, Value: error message

**Example JSON (General Error):**
```json
{
  "success": false,
  "message": "User with email(user@example.com) already exists",
  "path": "/api/v1/auth/register",
  "status": 400,
  "timestamp": "2025-11-25T11:00:49Z"
}
```

**Example JSON (Validation Error):**
```json
{
  "success": false,
  "message": "Validation failed",
  "path": "/api/v1/auth/login",
  "status": 400,
  "timestamp": "2025-11-25T11:00:49Z",
  "validationErrors": {
    "email": "Email should be valid",
    "password": "Password is required"
  }
}
```

---

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