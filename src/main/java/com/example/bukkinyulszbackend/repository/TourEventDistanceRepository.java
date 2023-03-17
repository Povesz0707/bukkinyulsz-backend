package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourEventDistanceRepository extends JpaRepository<TourEventDistance, Long> {
}
