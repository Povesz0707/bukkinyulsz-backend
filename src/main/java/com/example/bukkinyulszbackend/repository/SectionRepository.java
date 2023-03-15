package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Distance;
import com.example.bukkinyulszbackend.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
