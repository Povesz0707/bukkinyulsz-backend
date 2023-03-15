package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.Marking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkingRepository extends JpaRepository<Marking, Long> {
}
