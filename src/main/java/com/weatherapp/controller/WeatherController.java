package com.weatherapp.controller;

import com.weatherapp.model.WeatherData;
import com.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class WeatherController {

    private static final String[] DEFAULT_CITIES = {
            "London", "Tokyo", "New York", "Paris", "Mumbai", "Sydney", "Berlin", "Dubai"
    };

    @Autowired
    private WeatherService weatherService;

    private WeatherData lastWeatherData = null;

    @GetMapping("/")
    public String index(Model model) {
        String defaultCity = DEFAULT_CITIES[new Random().nextInt(DEFAULT_CITIES.length)];
        WeatherData weatherData = weatherService.getCurrentWeather(defaultCity);

        if (weatherData != null) {
            lastWeatherData = weatherData;
            model.addAttribute("weatherData", weatherData);
            model.addAttribute("forecast", weatherService.getMockForecast());
        }

        return "weather";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam("cityName") String cityName, Model model) {

        if (cityName == null || cityName.trim().isEmpty()) {
            model.addAttribute("error", "Please enter a city name.");
            if (lastWeatherData != null) {
                model.addAttribute("weatherData", lastWeatherData);
                model.addAttribute("forecast", weatherService.getMockForecast());
            }
            return "weather";
        }

        WeatherData weatherData = weatherService.getCurrentWeather(cityName.trim());

        if (weatherData != null) {
            lastWeatherData = weatherData;
            model.addAttribute("weatherData", weatherData);
            model.addAttribute("forecast", weatherService.getMockForecast());
        } else {
            model.addAttribute("error", "City not found. Showing last weather data.");
            if (lastWeatherData != null) {
                model.addAttribute("weatherData", lastWeatherData);
                model.addAttribute("forecast", weatherService.getMockForecast());
            }
        }

        return "weather";
    }
}