package com.example.infosyskaggleexercise.services;

import com.example.infosyskaggleexercise.exceptions.NoCsvFileException;
import com.example.infosyskaggleexercise.exceptions.WrongDateException;
import com.example.infosyskaggleexercise.exceptions.WrongInputException;
import com.example.infosyskaggleexercise.models.WorldData;
import com.example.infosyskaggleexercise.repository.USDataRepository;
import com.example.infosyskaggleexercise.repository.WorldDataRepository;
import com.example.infosyskaggleexercise.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlService {
    private final String COMMA_DELIMITER = ",";
    Logger log = LoggerFactory.getLogger(CrawlService.class);

    @Autowired
    private USDataRepository usRepo;

    @Autowired
    private WorldDataRepository worldRepo;

    // loads local CSV file data to DB
    public List<WorldData> loadWorldCsvData(String date)
            throws NoCsvFileException, WrongInputException, WrongDateException {
        // validate if input date is in range
        if (!validateDate(date)) return null;
        // List to hold a list of String tokens
        List<WorldData> records = new ArrayList<>();
        // filename by date
        String fileName = "./data/world/" + date + ".csv";

        try  {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // skip the header line of CSV file
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // replace string characters to ignore ',' in "Combined_Key"
                line = line.replace(", ", "-");
                log.debug(line);
                // tokens
                String[] values = line.split(COMMA_DELIMITER);

                // convert string to POJO
                WorldData datum = WorldData.builder()
                        .FIPS(values[0])
                        .admin2(values[1])
                        .provinceState(values[2])
                        .countryRegion(values[3])
                        .lastUpdate(Utils.toTimeStamp(values[4]))
                        .lat(Utils.toBigDecimal(values[5]))
                        .long_(Utils.toBigDecimal(values[6]))
                        .confirmed(Utils.toInteger(values[7]))
                        .deaths(Utils.toInteger(values[8]))
                        .recovered(Utils.toInteger(values[9]))
                        .active(Utils.toInteger(values[10]))
                        .combinedKey(values[11])
                        .incidentRate(Utils.toBigDecimal(values[12]))
                        .caseFatalityRatio(Utils.toBigDecimal(values[13]))
                        .build();
                records.add(datum);
            }
            // dealing with only 1 CSV file to crawl. Small portion.
            // hence, delete all and save all.
            worldRepo.deleteAllInBatch();
            worldRepo.saveAll(records);
            log.info("/crawl/world/" + date + " complete");
        } catch (FileNotFoundException e) {
            throw new NoCsvFileException(fileName);
        } catch (IOException e) {
            throw new WrongInputException();
        }
        return records;
    }

    private Boolean validateDate(String date) throws WrongDateException {
        // evaluate today. Local variable because global will evaluate when app runs
        LocalDate today = LocalDate.now();
        try {
            // parse input string to determine before/after today
            LocalDate input = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            // cannot have future date
            if (input.isAfter(today)) return false;
            else return true;
        } catch (DateTimeParseException e) {
            throw new WrongDateException(date);
        }
    }
}
