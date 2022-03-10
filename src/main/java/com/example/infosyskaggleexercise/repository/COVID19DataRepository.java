package com.example.infosyskaggleexercise.repository;

import com.example.infosyskaggleexercise.models.DBDatum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface COVID19DataRepository  extends JpaRepository<DBDatum, Integer>{

}
