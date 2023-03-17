package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Checkpoint;
import com.example.bukkinyulszbackend.model.SubSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
}
