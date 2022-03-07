package com.example.infosyskaggleexercise.dao;

import com.example.infosyskaggleexercise.models.COVID19Data;

import java.time.LocalDateTime;
import java.util.List;

public interface COVID19DataDao {
    List<COVID19Data> getAll(LocalDateTime date);
}
