package com.example.infosyskaggleexercise.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class COVID19Data {
    private String FIPS;
    private String admin2;
    private String provinceState;
    private String countryRegion;
    private LocalDateTime lastUpdate;
    private BigDecimal lat;
    private BigDecimal long_;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer active;
    private String combinedKey;
    private BigDecimal incidentRate;
    private BigDecimal caseFatalityRatio;
}
