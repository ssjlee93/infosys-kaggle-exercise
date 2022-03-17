package com.example.infosyskaggleexercise.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/*
Model for COVID-19 Data by countries worldwide
 */

// JPA
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorldData {
    // Commented out omitted fields from CSV file
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    //    private String FIPS;
    private String admin2;
    private String provinceState;
    private String countryRegion;
    private Timestamp lastUpdate;
    //    private BigDecimal lat;
    //    private BigDecimal long_;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer active;
    private BigDecimal incidentRate;
    private BigDecimal caseFatalityRatio;
    //    private String combinedKey;

}
