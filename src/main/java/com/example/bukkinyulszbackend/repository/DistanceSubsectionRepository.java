package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.DistanceSubsection;
import com.example.bukkinyulszbackend.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistanceSubsectionRepository extends JpaRepository<DistanceSubsection, Long> {
    List<DistanceSubsection> findAllByDistance(Distance distance);
    List<DistanceSubsection> findAllBySubSection(SubSection subSection);
}
