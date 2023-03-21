package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.TourEventDistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourEventDistanceRepository extends JpaRepository<TourEventDistance, Long> {
    List<TourEventDistance> findAllByDistance(Distance distance);
    List<TourEventDistance> findAllByTourEvent(TourEvent tourEvent);
}
