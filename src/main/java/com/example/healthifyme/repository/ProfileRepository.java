package com.example.healthifyme.repository;

import com.example.healthifyme.entity.Profile;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID>{

    Optional<Profile> findByUserId(UUID userId);

}
