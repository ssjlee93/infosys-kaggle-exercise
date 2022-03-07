package com.example.infosyskaggleexercise.dao;

import com.example.infosyskaggleexercise.models.COVID19Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class COVID19DataDaoFileImpl implements COVID19DataDao{
    private List<COVID19Data> data = new ArrayList<>();
    private String path = "./data/";


    @Override
    public List<COVID19Data> getAll(LocalDateTime date) {
        return null;
    }
}
