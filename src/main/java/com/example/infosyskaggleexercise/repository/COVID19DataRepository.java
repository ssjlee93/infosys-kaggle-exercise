package com.example.infosyskaggleexercise.repository;

import com.example.infosyskaggleexercise.models.CSVDatum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface COVID19DataRepository  extends JpaRepository<CSVDatum, Integer>{

}
