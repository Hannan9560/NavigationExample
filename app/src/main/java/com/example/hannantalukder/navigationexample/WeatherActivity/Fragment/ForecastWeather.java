package com.example.hannantalukder.navigationexample.WeatherActivity.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hannantalukder.navigationexample.MainActivity;
import com.example.hannantalukder.navigationexample.R;
import com.example.hannantalukder.navigationexample.WeatherActivity.Adapter.ForeCastAdapter;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.CitySelection;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.MenuService;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.WeatherApi;
import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.WeatherForecastModelClass;
import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.WeatherModelClass;
import com.example.hannantalukder.navigationexample.WeatherActivity.Weather_Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static com.example.hannantalukder.navigationexample.WeatherActivity.Weather_Activity.dataType;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastWeather extends Fragment {

    private final String BASE_URL = "http://api.openweathermap.org";
    private String TAG = "WeatherApp";
    private WeatherApi weatherApi;
    private MenuService menuService;
    private CitySelection citySelection;
    private Context context;
    private View inflatedView;
    private ListView forecastWeatherLv;
    private WeatherForecastModelClass weatherForecastData;
    private ArrayList<WeatherModelClass> weatherData;
    private boolean temperatureFormatCelsius = true;
    private String queryCity;
    public ForecastWeather() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView =  inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        menuService = new Weather_Activity();
        dataType = menuService.getData();
        citySelection = new Weather_Activity();
        queryCity = citySelection.getCity();
        initializeAll();
        return inflatedView;
    }

    private void initializeAll() {
        forecastWeatherLv = (ListView) inflatedView.findViewById(R.id.weatherForecastLv);
        weatherData = new ArrayList<>();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherForecastModelClass> getWeatherData = weatherApi.getWeatherData("/data/2.5/forecast/daily?q="+queryCity+"&cnt=10"+"&units="+dataType+"&appid=a226ec225f23ea5717f7fa94ce785237");
        getWeatherData.enqueue(new Callback<WeatherForecastModelClass>() {
            @Override
            public void onResponse(Response<WeatherForecastModelClass> response, Retrofit retrofit) {
                weatherForecastData = response.body();
                java.util.List<WeatherForecastModelClass.List> weatherList = weatherForecastData.getList();

                //retrieve data and store into weatherData arrayList
                for(WeatherForecastModelClass.List weather : weatherList){

                    int day = weather.getDt();
                    String W_day= new SimpleDateFormat("EEEE").format(new Date(day * 1000L));
                    String date= new SimpleDateFormat("dd-MM-yyyy").format(new Date(day * 1000L));
                    double maximumTemp = weather.getTemp().getMax();
                    double minimumTemp = weather.getTemp().getMin();
                    String Icon = weather.getIcon();
                    String description = weather.getWeather().get(0).getDescription();
                    double speed = weather.getSpeed();
                    int cloud = weather.getClouds();
                    double pressure = weather.getPressure();
                    weatherData.add(new WeatherModelClass(W_day,date,Icon,maximumTemp,minimumTemp,description,speed,cloud,pressure));
                }
                updateListView();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
            
        });
    }
    //update weather forecast ListView
    public void updateListView(){
        if (weatherData.size() > 0 ){
            ForeCastAdapter adapter = new ForeCastAdapter(context,weatherData,temperatureFormatCelsius);
            forecastWeatherLv.setAdapter(adapter);
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}