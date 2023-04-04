package com.example.bukkinyulszbackend.repository;

import com.example.bukkinyulszbackend.model.TourEvent;
import com.example.bukkinyulszbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<List<User>> findAllByEmail(String email);
    @Query(value = "SELECT DISTINCT u FROM User AS u WHERE u.username = :username OR CAST(u.email AS text) = :username")
    List<User> queryLogin(String username);
}
