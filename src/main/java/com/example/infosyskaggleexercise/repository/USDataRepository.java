package com.example.infosyskaggleexercise.repository;

import com.example.infosyskaggleexercise.models.WorldData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface USDataRepository extends JpaRepository<WorldData, Integer> {
}
