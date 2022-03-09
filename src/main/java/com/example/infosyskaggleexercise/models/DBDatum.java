package com.example.infosyskaggleexercise.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA
@Entity
public class DBDatum {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;


}
