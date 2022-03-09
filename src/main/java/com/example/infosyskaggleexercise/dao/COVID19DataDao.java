package com.example.infosyskaggleexercise.dao;

import com.example.infosyskaggleexercise.models.CSVDatum;

import java.time.LocalDateTime;
import java.util.List;

public interface COVID19DataDao {
    List<CSVDatum> getAll(LocalDateTime date);
}
