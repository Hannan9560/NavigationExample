package com.example.hannantalukder.navigationexample.WeatherActivity.Interface;

import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.CurrentWeatherModelClass;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public interface WeatherApiCurrent {
    @GET()
    Call<CurrentWeatherModelClass> getCurrentData(@Url String stringUrl);
}
