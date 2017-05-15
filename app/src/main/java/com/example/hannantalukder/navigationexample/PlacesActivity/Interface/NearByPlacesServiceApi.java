package com.example.hannantalukder.navigationexample.PlacesActivity.Interface;

import com.example.hannantalukder.navigationexample.PlacesActivity.ModelClass.NearByPlacesClass;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Url;

/**
 * Created by Hannan Talukder on 5/15/2017.
 */

public interface NearByPlacesServiceApi {
    @GET
    Call<NearByPlacesClass> getNearByPlacesClassCall(@Url String stringUrl);
}
