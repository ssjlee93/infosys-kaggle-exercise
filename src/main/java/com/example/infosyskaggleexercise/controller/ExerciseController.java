package com.example.infosyskaggleexercise.controller;

import com.example.infosyskaggleexercise.models.COVID19;
import com.example.infosyskaggleexercise.repository.COVID19DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ExerciseController {
    private final String COMMA_DELIMITER = ",";
    Logger log = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    COVID19DataRepository repository;

    // landing page
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // GET request for CSV Data
    @GetMapping(value={"/crawl/{date}"})
    public List<COVID19> crawlData(@PathVariable String date) {
        // List to hold a list of String tokens
        List<COVID19> records = new ArrayList<>();
        // filename by date
        String fileName = "./data/" + date + ".csv";

        try  {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // replace string characters to ignore ',' in "Combined_Key"
                line = line.replace(", ", "-");
                log.info(line);
                // tokens
                String[] values = line.split(COMMA_DELIMITER);

                // convert string to POJO
                COVID19 datum = COVID19.builder()
//                        .FIPS(values[0])
                        .admin2(values[1])
                        .provinceState(values[2])
                        .countryRegion(values[3])
                        .lastUpdate(values[4].isEmpty()?null:Timestamp.valueOf(LocalDateTime.parse(values[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
//                        .lat(values[5].isEmpty()?null:BigDecimal.valueOf(Double.valueOf(values[5])))
//                        .long_(values[6].isEmpty()?null:BigDecimal.valueOf(Double.valueOf(values[6])))
                        .confirmed(values[7].isEmpty()?null:Integer.parseInt(values[7]))
                        .deaths(values[8].isEmpty()?null:Integer.parseInt(values[8]))
                        .recovered(values[9].isEmpty()?null:Integer.parseInt(values[9]))
                        .active(values[10].isEmpty()?null:Integer.parseInt(values[10]))
//                        .combinedKey(values[11])
//                        .incidentRate(values[12].isEmpty()?null:BigDecimal.valueOf(Double.valueOf(values[12])))
//                        .caseFatalityRatio(values[13].isEmpty()?null:BigDecimal.valueOf(Double.valueOf(values[13])))
                        .build();
                records.add(datum);
            }
            // dealing with only 1 CSV file to crawl. Small portion.
            // hence, delete all and save all.
            repository.deleteAllInBatch();
            repository.saveAll(records);
        } catch (FileNotFoundException e) {
            log.error("No file");
        } catch (IOException e) {
            log.error("IO exception");
        }
        return records;
    }

    // GET Request for receiving data from DB
    @GetMapping("/data")
    public List<COVID19> getData() {
        return repository.findAll();
    }

    // Spring boot Angular security
    @GetMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        log.info((String) model.get("id"));
        return model;
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        log.info("/user : " + user.getName());
        return user;
    }

    // Enabling Cross Origin Requests for a RESTful Web Service
    // https://spring.io/guides/gs/rest-service-cors/#global-cors-configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }
}
