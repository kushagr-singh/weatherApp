package com.weatherapp.model;

public class WeatherData {
    private String cityName;
    private String country;
    private double temperature;
    private double feelsLike;
    private String weatherCondition;
    private String weatherDescription;
    private int humidity;
    private int pressure;
    private double windSpeed;
    private int windDeg;
    private double uvIndex;
    private int visibility;
    private String sunrise;
    private String sunset;
    private String weatherIcon;
    private String cityDate;
    private String cityTime;
    private String backgroundClass;
    private long timezone;

    // Constructors
    public WeatherData() {}

    public WeatherData(String cityName, String country, double temperature,
                       double feelsLike, String weatherCondition) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.weatherCondition = weatherCondition;
    }

    // Getters and Setters
    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    public String getWeatherCondition() { return weatherCondition; }
    public void setWeatherCondition(String weatherCondition) { this.weatherCondition = weatherCondition; }

    public String getWeatherDescription() { return weatherDescription; }
    public void setWeatherDescription(String weatherDescription) { this.weatherDescription = weatherDescription; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public int getPressure() { return pressure; }
    public void setPressure(int pressure) { this.pressure = pressure; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public int getWindDeg() { return windDeg; }
    public void setWindDeg(int windDeg) { this.windDeg = windDeg; }

    public double getUvIndex() { return uvIndex; }
    public void setUvIndex(double uvIndex) { this.uvIndex = uvIndex; }

    public int getVisibility() { return visibility; }
    public void setVisibility(int visibility) { this.visibility = visibility; }

    public String getSunrise() { return sunrise; }
    public void setSunrise(String sunrise) { this.sunrise = sunrise; }

    public String getSunset() { return sunset; }
    public void setSunset(String sunset) { this.sunset = sunset; }

    public String getWeatherIcon() { return weatherIcon; }
    public void setWeatherIcon(String weatherIcon) { this.weatherIcon = weatherIcon; }

    public String getCityDate() { return cityDate; }
    public void setCityDate(String cityDate) { this.cityDate = cityDate; }

    public String getCityTime() { return cityTime; }
    public void setCityTime(String cityTime) { this.cityTime = cityTime; }

    public String getBackgroundClass() { return backgroundClass; }
    public void setBackgroundClass(String backgroundClass) { this.backgroundClass = backgroundClass; }

    public long getTimezone() { return timezone; }
    public void setTimezone(long timezone) { this.timezone = timezone; }
}