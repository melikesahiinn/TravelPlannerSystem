package com.travelplanner.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.travelplanner.model.City;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class CityRepository {

    private static CityRepository instance;
    private List<City> cities;

    private CityRepository() {
        loadCities();
    }

    public static CityRepository getInstance() {

        if(instance == null) {
            instance = new CityRepository();
        }

        return instance;
    }

    private void loadCities() {

        try {

            Gson gson = new Gson();

            Type type = new TypeToken<List<City>>(){}.getType();

            cities = gson.fromJson(
                new InputStreamReader(
                    getClass().getResourceAsStream("/cities.json")
                ),
                type
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<City> getCities() {
        return cities;
    }
}