package com.example.infosyskaggleexercise.services;

import com.example.infosyskaggleexercise.exceptions.NoCsvFileException;
import com.example.infosyskaggleexercise.exceptions.WrongDateException;
import com.example.infosyskaggleexercise.exceptions.WrongInputException;
import com.example.infosyskaggleexercise.models.WorldData;
import com.example.infosyskaggleexercise.repository.WorldDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CrawlServiceTest {

    @Mock
    WorldDataRepository worldRepo;

    @Autowired
    CrawlService crawlService;

    @Test
    void testLoadingWorldCsvData() {
        // TODO add unit tests for the successful case

        // create mock data
        String date = "test";
        // data being tested :  ,,,testCountry,2022-03-16 04:20:50,33.93911,67.709953,11111,1000,1100,1110,testCountry,453.5795655920708,4.330269410039021

        // test with try catch block
        try {
            List<WorldData> testData = crawlService.loadWorldCsvData(date);
            // assert not null returns
            assertNotNull(testData);
            // assert equals
        } catch (NoCsvFileException e) {
            fail("no file found");
        } catch (WrongDateException e) {
            fail("wrong date input");
        } catch (WrongInputException e) {
            fail("wrong data input");
        }


    }

    // TODO test NoCsvFileException

    // TODO test WrongDateException

    // TODO test WrongInputException
}