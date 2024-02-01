package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
    public List<Tender> findAllByActiveIsTrue();
}
