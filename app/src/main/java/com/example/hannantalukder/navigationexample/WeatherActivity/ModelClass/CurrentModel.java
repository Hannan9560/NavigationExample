package com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public class CurrentModel {
    private String Cloud;
    private String Temperature;
    private String Temperature_Max;
    private String Temperature_Mini;
    private String Humidity;
    private String Pressure;
    private String Wind;
    private String Direction;
    private String Description;
    private int SunSetTime;
    private int SunRiseTime;

    public CurrentModel(String cloud, String temperature, String temperature_Max, String temperature_Mini, String humidity, String pressure, String wind, String direction, String description, int sunSetTime, int sunRiseTime) {
        Cloud = cloud;
        Temperature = temperature;
        Temperature_Max = temperature_Max;
        Temperature_Mini = temperature_Mini;
        Humidity = humidity;
        Pressure = pressure;
        Wind = wind;
        Direction = direction;
        Description = description;
        SunSetTime = sunSetTime;
        SunRiseTime = sunRiseTime;
    }

    public CurrentModel(CurrentWeatherModelClass currentWeatherModelClass) {
        this.Convert(currentWeatherModelClass);
    }

    private void Convert(CurrentWeatherModelClass currentWeatherModelClass) {
        setCloud(String.valueOf(currentWeatherModelClass.getClouds()));
        setTemperature(String.valueOf(currentWeatherModelClass.getMain().getTemp()));
        setTemperature_Mini(String.valueOf(currentWeatherModelClass.getMain().getTempMin()));
        setTemperature_Max(String.valueOf(currentWeatherModelClass.getMain().getTempMax()));
        setHumidity(String.valueOf(currentWeatherModelClass.getMain().getHumidity()));
        setPressure(String.valueOf(currentWeatherModelClass.getMain().getPressure()));
        setWind(String.valueOf(currentWeatherModelClass.getWind().getSpeed()));
        setDirection(String.valueOf(currentWeatherModelClass.getWind().getDeg()));
        setDescription(String.valueOf(currentWeatherModelClass.getWeather().get(0).getDescription()));
        setSunRiseTime(Integer.parseInt(String.valueOf(currentWeatherModelClass.getSys().getSunrise())));
        setSunSetTime(Integer.parseInt(String.valueOf(currentWeatherModelClass.getSys().getSunset())));

    }

    public String getCloud() {
        return Cloud;
    }

    public void setCloud(String cloud) {
        Cloud = cloud;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getTemperature_Max() {
        return Temperature_Max;
    }

    public void setTemperature_Max(String temperature_Max) {
        Temperature_Max = temperature_Max;
    }

    public String getTemperature_Mini() {
        return Temperature_Mini;
    }

    public void setTemperature_Mini(String temperature_Mini) {
        Temperature_Mini = temperature_Mini;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getSunSetTime() {
        return SunSetTime;
    }

    public void setSunSetTime(int sunSetTime) {
        SunSetTime = sunSetTime;
    }

    public int getSunRiseTime() {
        return SunRiseTime;
    }

    public void setSunRiseTime(int sunRiseTime) {
        SunRiseTime = sunRiseTime;
    }
}


