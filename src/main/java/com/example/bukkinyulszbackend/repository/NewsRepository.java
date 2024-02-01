package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByActiveIsTrueOrderByInsertedDesc();
    List<News> findTop3ByActiveIsTrueOrderByInsertedDesc();
    List<News> findAllByActiveIsTrue();
}
