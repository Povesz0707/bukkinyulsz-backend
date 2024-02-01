package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.GaleryImage;
import com.example.bukkinyulszbackend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaleryImageRepository extends JpaRepository<GaleryImage, Long> {
    List<GaleryImage> findAllByActiveIsTrueOrderByInsertedDesc();
}
