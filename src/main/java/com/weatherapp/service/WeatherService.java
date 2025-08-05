package com.weatherapp.service;

import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.uv.url}")
    private String uvApiUrl;

    private final RestTemplate restTemplate;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    public WeatherData getCurrentWeather(String cityName) {
        try {
            String url = apiUrl + "?q=" + cityName + "&appid=" + apiKey + "&units=metric";

            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

            if (response != null) {
                WeatherData weatherData = mapToWeatherData(response);

                // Fetch UV index using coordinates
                double lat = response.getCoord().getLat();
                double lon = response.getCoord().getLon();

                Double uvIndex = fetchUvIndex(lat, lon);
                weatherData.setUvIndex(uvIndex != null ? uvIndex : -1);

                // Set visibility
                if (response.getVisibility() != null) {
                    weatherData.setVisibility(response.getVisibility());
                } else {
                    weatherData.setVisibility(-1);
                }

                // Calculate city-specific time and date
                setCityDateTime(weatherData, response.getTimezone());

                return weatherData;
            }

            return null;
        } catch (HttpClientErrorException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private WeatherData mapToWeatherData(WeatherResponse response) {
        WeatherData weatherData = new WeatherData();

        // Basic info
        weatherData.setCityName(response.getName());
        weatherData.setCountry(response.getSys().getCountry());
        weatherData.setTimezone(response.getTimezone());

        // Temperature data
        weatherData.setTemperature(Math.round(response.getMain().getTemp()));
        weatherData.setFeelsLike(Math.round(response.getMain().getFeelsLike()));
        weatherData.setHumidity(response.getMain().getHumidity());
        weatherData.setPressure(response.getMain().getPressure());

        // Weather condition
        WeatherResponse.Weather weather = response.getWeather()[0];
        weatherData.setWeatherCondition(weather.getMain());
        weatherData.setWeatherDescription(capitalizeFirstLetter(weather.getDescription()));

        // Wind data
        if (response.getWind() != null) {
            weatherData.setWindSpeed(response.getWind().getSpeed());
            weatherData.setWindDeg(response.getWind().getDeg());
        }

        // Sunrise and sunset (convert to city time)
        weatherData.setSunrise(formatTimeWithTimezone(response.getSys().getSunrise(), response.getTimezone()));
        weatherData.setSunset(formatTimeWithTimezone(response.getSys().getSunset(), response.getTimezone()));

        // Weather icon and background
        weatherData.setWeatherIcon(getWeatherEmoji(weatherData.getWeatherCondition()));
        weatherData.setBackgroundClass(getBackgroundClass(weatherData.getWeatherCondition()));

        return weatherData;
    }

    private void setCityDateTime(WeatherData weatherData, long timezoneOffset) {
        try {
            // Get current UTC time and apply city's timezone offset
            Instant now = Instant.now();
            ZonedDateTime cityTime = now.atOffset(ZoneOffset.ofTotalSeconds((int) timezoneOffset))
                    .atZoneSameInstant(ZoneOffset.ofTotalSeconds((int) timezoneOffset));

            // Format date and time
            String cityDate = cityTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"));
            String cityTimeStr = cityTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

            weatherData.setCityDate(cityDate);
            weatherData.setCityTime(cityTimeStr);
        } catch (Exception e) {
            // Fallback to UTC time
            weatherData.setCityDate("Date unavailable");
            weatherData.setCityTime("Time unavailable");
        }
    }

    private String formatTimeWithTimezone(long timestamp, long timezoneOffset) {
        try {
            Instant instant = Instant.ofEpochSecond(timestamp);
            ZonedDateTime cityTime = instant.atOffset(ZoneOffset.ofTotalSeconds((int) timezoneOffset))
                    .atZoneSameInstant(ZoneOffset.ofTotalSeconds((int) timezoneOffset));
            return cityTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (Exception e) {
            Date date = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            return sdf.format(date);
        }
    }

    private Double fetchUvIndex(double lat, double lon) {
        try {
            String url = uvApiUrl + "?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey;

            Map<String, Object> uvResponse = restTemplate.getForObject(url, Map.class);

            if (uvResponse != null && uvResponse.containsKey("value")) {
                Object val = uvResponse.get("value");
                if (val instanceof Number) {
                    return ((Number) val).doubleValue();
                }
            }
        } catch (Exception e) {
            // Ignore UV fetch errors
        }
        return null;
    }

    private String getWeatherEmoji(String weatherCondition) {
        return switch (weatherCondition.toLowerCase()) {
            case "clear" -> "☀️";
            case "clouds" -> "☁️";
            case "rain", "drizzle" -> "🌧️";
            case "thunderstorm" -> "⛈️";
            case "snow" -> "❄️";
            case "mist", "fog", "haze" -> "🌫️";
            default -> "🌤️";
        };
    }

    private String getBackgroundClass(String weatherCondition) {
        return switch (weatherCondition.toLowerCase()) {
            case "clear" -> "sunny";
            case "clouds" -> "cloudy";
            case "rain", "drizzle" -> "rainy";
            case "thunderstorm" -> "stormy";
            case "snow" -> "snowy";
            default -> "partly-cloudy";
        };
    }

    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public Object[][] getMockForecast() {
        return new Object[][]{
                {"Today", "🌤️", "22°C", "Partly Cloudy"},
                {"Tomorrow", "🌧️", "18°C", "Light Rain"},
                {"Tuesday", "☁️", "20°C", "Cloudy"},
                {"Wednesday", "☀️", "25°C", "Sunny"},
                {"Thursday", "🌦️", "19°C", "Scattered Showers"}
        };
    }
}