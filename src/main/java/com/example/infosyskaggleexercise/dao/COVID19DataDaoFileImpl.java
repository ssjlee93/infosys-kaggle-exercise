package com.example.infosyskaggleexercise.dao;

import com.example.infosyskaggleexercise.models.CSVDatum;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class COVID19DataDaoFileImpl implements COVID19DataDao{
    private List<CSVDatum> data = new ArrayList<>();
    private String path = "./data/";


    @Override
    public List<CSVDatum> getAll(LocalDateTime date) {
        return null;
    }
}
