package com.example.infosyskaggleexercise.repository;

import com.example.infosyskaggleexercise.models.COVID19;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface COVID19DataRepository  extends JpaRepository<COVID19, Integer>{

}
