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

// JPA
@Entity
// lombok
@Data       // getter, setter, equalsAndHashcode
@Builder    // builder pattern
@AllArgsConstructor
@NoArgsConstructor
public class UsData {
    // Commented out omitted fields from CSV file
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String provinceState;
    private String countryRegion;
    private Timestamp lastUpdate;
    private BigDecimal lat;
    private BigDecimal long_;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer active;
    private String FIPS;
    private BigDecimal incidentRate;
    private BigDecimal totalTestResults;
    private Integer peopleHospitalized;
    private BigDecimal caseFatalityRatio;
    private BigDecimal UID;
    private String ISO3;
    private BigDecimal testingRate;
    private BigDecimal hospitalizationRate;

}
