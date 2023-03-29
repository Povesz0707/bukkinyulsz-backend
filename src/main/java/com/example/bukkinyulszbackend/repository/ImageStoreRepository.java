package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageStoreRepository extends JpaRepository<FileStore, Long> {
}
