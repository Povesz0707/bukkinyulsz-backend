package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceRepository extends JpaRepository <Distance, Long>{
}
