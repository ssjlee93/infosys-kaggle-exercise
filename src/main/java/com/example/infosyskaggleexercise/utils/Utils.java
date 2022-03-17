package com.example.infosyskaggleexercise.utils;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Utilities {

    // converts String to Integer wrapper class
    public Integer toInteger(String s) {
        return s.isEmpty()?null:Integer.parseInt(s);
    }

    // converts String to BigDecimal wrapper class - for accuracy
    public BigDecimal toBigDecimal(String s) {
        return s.isEmpty()?null:new BigDecimal(s);
    }

    // converts String in the format yyyy-MM-dd HH:mm:ss to SQLTimestamp
    public Timestamp toTimeStamp(String s) {
        return s.isEmpty()?null:Timestamp.valueOf(LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
