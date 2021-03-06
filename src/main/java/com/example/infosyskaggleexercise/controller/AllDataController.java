package com.example.infosyskaggleexercise.controller;

import com.example.infosyskaggleexercise.exceptions.NoCsvFileException;
import com.example.infosyskaggleexercise.exceptions.WrongDateException;
import com.example.infosyskaggleexercise.exceptions.WrongInputException;
import com.example.infosyskaggleexercise.models.UsData;
import com.example.infosyskaggleexercise.models.WorldData;
import com.example.infosyskaggleexercise.repository.USDataRepository;
import com.example.infosyskaggleexercise.repository.WorldDataRepository;
import com.example.infosyskaggleexercise.services.CrawlService;
import com.example.infosyskaggleexercise.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AllDataController {
    private final String COMMA_DELIMITER = ",";
    Logger log = LoggerFactory.getLogger(AllDataController.class);

    @Autowired
    CrawlService crawlService;

    @Autowired
    WorldDataRepository worldRepo;

    @Autowired
    USDataRepository usRepo;

    // landing page
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // GET request for CSV Data
    // world data by countries
    @GetMapping(value={"/crawl/world/{date}"})
    public List<WorldData> crawlWorldData(@PathVariable String date) {

        try  {
            log.info("/crawl/world/" + date + " complete");
            return crawlService.loadWorldCsvData(date);
        } catch (NoCsvFileException e) {
            log.error("/crawl/world : No file");
        } catch (WrongInputException e) {
            log.error("/crawl/world : IO exception");
        } catch (WrongDateException e) {
            log.error("/crawl/world : Wrong date input" + date);
        }
        // return null if try block fails
        return null;
    }

    // US data by state
    @GetMapping(value={"/crawl/US/{date}"})
    public List<UsData> crawlUSData(@PathVariable String date) {
        // TODO move to service layer

        // List to hold a list of String tokens
        List<UsData> records = new ArrayList<>();
        // filename by date
        String fileName = "./data/us/" + date + ".csv";

        try  {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // skip header row
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                log.debug(line);
                // tokens
                String[] values = line.split(COMMA_DELIMITER, 18);

                // convert string to POJO
                UsData datum = UsData.builder()
                        .provinceState(values[0])
                        .countryRegion(values[1])
                        .lastUpdate(Utils.toTimeStamp(values[2]))
                        .lat(Utils.toBigDecimal(values[3]))
                        .long_(Utils.toBigDecimal(values[4]))
                        .confirmed(Utils.toInteger(values[5]))
                        .deaths(Utils.toInteger(values[6]))
                        .recovered(Utils.toInteger(values[7]))
                        .active(Utils.toInteger(values[8]))
                        .FIPS(values[9])
                        .incidentRate(Utils.toBigDecimal(values[10]))
                        .totalTestResults(Utils.toBigDecimal(values[11]))
                        .peopleHospitalized(Utils.toInteger(values[12]))
                        .caseFatalityRatio(Utils.toBigDecimal(values[13]))
                        .UID(Utils.toBigDecimal(values[14]))
                        .ISO3(values[15])
                        .testingRate(Utils.toBigDecimal(values[16]))
                        .hospitalizationRate(Utils.toBigDecimal(values[17]))
                        .build();
                records.add(datum);
            }
            // dealing with only 1 CSV file to crawl. Small portion.
            // hence, delete all and save all.
            usRepo.deleteAllInBatch();
            usRepo.saveAll(records);
            log.info("/crawl/us/" + date + " complete");
        } catch (FileNotFoundException e) {
            log.error("/crawl/us/ : No file");
        } catch (IOException e) {
            log.error("/crawl/us/ : IO exception");
        }
        return records;
    }

    // GET Request for receiving world data from DB
    @GetMapping("/world")
    public List<WorldData> getWorldData() {
        // TODO move to service layer

        log.info("/world : sending world data");
        return worldRepo.findAll();
    }

    // GET Request for receiving us data from DB
    @GetMapping("/us")
    public List<UsData> getUsData() {
        // TODO move to service layer

        log.info("/world : sending world data");
        return usRepo.findAll();
    }

    // Spring boot Angular security
    // TODO remove once Keycloak is implemented
    @GetMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        log.info("/resource : id " + (String) model.get("id"));
        return model;
    }

    // TODO delegate user authentication to Keycloak
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
