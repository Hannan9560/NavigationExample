package com.example.hannantalukder.navigationexample.WeatherActivity.Interface;

import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.WeatherForecastModelClass;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public interface WeatherApi {
    @GET()
    Call<WeatherForecastModelClass> getWeatherData(@Url String stringUrl);
}
