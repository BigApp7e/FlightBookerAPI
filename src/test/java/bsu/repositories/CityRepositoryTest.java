package bsu.repositories;

import bsu.entities.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@SpringBootTest
class CityRepositoryTest {

    @Autowired
    private CityRepository repository;

    @Test
    void sendValidRequestAndVerifyResult() {
        List<String> names = new ArrayList<>(Arrays.asList("Sofia", "London"));
        List<City> result = repository.findByNameIn(names);
        Assertions.assertTrue(result.size()>0);
    }

    @Test
    void sendInvalidDataRequestAndConfirmEmptyResult() {
        List<String> names = new ArrayList<>(Arrays.asList("Karnobat", "Yambol"));
        List<City> result = repository.findByNameIn(names);
        Assertions.assertTrue(result.size()==0);
    }
}