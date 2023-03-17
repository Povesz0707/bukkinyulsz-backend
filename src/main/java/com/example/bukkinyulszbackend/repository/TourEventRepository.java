package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.SubSection;
import com.example.bukkinyulszbackend.model.TourEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourEventRepository extends JpaRepository<TourEvent, Long> {
    TourEvent findFirstByActiveIsTrueOrderByDateOfEventDesc();
}
