package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.CompetitorRegistration;
import com.example.bukkinyulszbackend.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorRegistrationRepository extends JpaRepository<CompetitorRegistration, Long> {
}
