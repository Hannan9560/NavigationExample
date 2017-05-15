package com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public class WeatherModelClass {
    private String weatherDay;
    private String weatherDate;
    private String weatherIcon;
    private double highTemp;
    private double lowTemp;
    private String description;
    private double speed;
    private int cloud;
    private double pressure;

    public WeatherModelClass(String weatherDay, String weatherDate, double highTemp, double lowTemp, String description, double speed, int cloud, double pressure) {
        this.weatherDay = weatherDay;
        this.weatherDate = weatherDate;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.description = description;
        this.speed = speed;
        this.cloud = cloud;
        this.pressure = pressure;
    }

    public WeatherModelClass(String weatherDay, String weatherDate, String weatherIcon, double highTemp, double lowTemp, String description, double speed, int cloud, double pressure) {
        this.weatherDay = weatherDay;
        this.weatherDate = weatherDate;
        this.weatherIcon = weatherIcon;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.description = description;
        this.speed = speed;
        this.cloud = cloud;
        this.pressure = pressure;
    }

    public WeatherModelClass() {
    }

    public String getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(String weatherDay) {
        this.weatherDay = weatherDay;
    }

    public String getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public double getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(double highTemp) {
        this.highTemp = highTemp;
    }

    public double getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(double lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
