package com.travelplanner.model;

public class City {

    private String name;
    private int population;
    private double area;
    private double currentTemperature;
    private WeatherState currentWeatherState;

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public WeatherState getCurrentWeatherState() {
        return currentWeatherState;
    }

    @Override
    public String toString() {
        return name + " - " + currentWeatherState;
    }
}