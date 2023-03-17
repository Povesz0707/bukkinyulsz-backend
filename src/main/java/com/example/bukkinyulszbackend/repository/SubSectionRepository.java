package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSectionRepository extends JpaRepository<SubSection, Long> {
}
