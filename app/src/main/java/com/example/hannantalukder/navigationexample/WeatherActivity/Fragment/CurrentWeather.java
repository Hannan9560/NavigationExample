package com.example.hannantalukder.navigationexample.WeatherActivity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hannantalukder.navigationexample.MainActivity;
import com.example.hannantalukder.navigationexample.R;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.CitySelection;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.LocationInterface;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.MenuService;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.WeatherApiCurrent;
import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.CurrentModel;
import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.CurrentWeatherModelClass;
import com.example.hannantalukder.navigationexample.WeatherActivity.Weather_Activity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeather extends Fragment {


    private MenuService menuService;
    private CitySelection citySelection;
    private LocationInterface locationInterface;
    private TextView currentTempTV;
    private TextView windTV;
    private TextView descriptionTV;
    private TextView pressureTV;
    private TextView humanityTV;
    private TextView sunriseTV;
    private TextView sunsetTV;
    private TextView currentDescriptionTV;
    private ImageView currentIcon;

    private final String BASE_URL = "http://api.openweathermap.org";
    private WeatherApiCurrent apiRespons;
    private String dataType;
    private String celcious;

    /*Location*/
    private String addressLine;
    private String currentcity;
    private String queryCity;
    private TextView addressTV;
    public CurrentWeather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_weather, container, false);

        menuService = new Weather_Activity();
        dataType = menuService.getData();

        citySelection = new Weather_Activity();
        locationInterface = new Weather_Activity();

        citySelection = new Weather_Activity();
        queryCity = citySelection.getCity();

        addressLine = locationInterface.getlocationAddressLine();
        currentcity = locationInterface.getlocationCity();
        celcious = menuService.getCelcious();

        /*city = (TextView) v.findViewById(R.id.city);*/
        currentTempTV = (TextView) v.findViewById(R.id.currenttempTV);
        windTV = (TextView) v.findViewById(R.id.windTV);
        descriptionTV = (TextView) v.findViewById(R.id.descriptionCloudTV);
        pressureTV = (TextView) v.findViewById(R.id.pressureCTV);
        humanityTV = (TextView) v.findViewById(R.id.humanityTV);
        sunriseTV = (TextView) v.findViewById(R.id.sunriseTV);
        sunsetTV = (TextView) v.findViewById(R.id.sunsetTV);
        currentDescriptionTV = (TextView) v.findViewById(R.id.descriptionCurrentTV);
        currentIcon = (ImageView) v.findViewById(R.id.currentIcon);

        /*Location*/
        addressTV = (TextView) v.findViewById(R.id.addressTV);
        addressTV.setText(addressLine+"\n"+currentcity);

        CurrentWeatherData();
        return v;
    }

    private void CurrentWeatherData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiRespons = retrofit.create(WeatherApiCurrent.class);
        Call<CurrentWeatherModelClass> arrayListCall = apiRespons
                .getCurrentData("/data/2.5/weather?q="+queryCity+"&units="+dataType+"&appid=a226ec225f23ea5717f7fa94ce785237");

        arrayListCall.enqueue(new Callback<CurrentWeatherModelClass>() {
            @Override
            public void onResponse(Response<CurrentWeatherModelClass> response, Retrofit retrofit) {
                if(response.code()==200)
                {
                    CurrentWeatherModelClass currentWeatherModelClass = response.body();
                    setData(currentWeatherModelClass);
                }
                else
                {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }
    private void setData(CurrentWeatherModelClass currentWeatherModelClass) {
        CurrentModel currentModel = new CurrentModel(currentWeatherModelClass);
        currentTempTV.setText(currentModel.getTemperature()+"°"+celcious);
        Picasso.with(getContext()).load("http://openweathermap.org/img/w/"+currentWeatherModelClass.getWeather().get(0).getIcon()+".png").into(currentIcon);
        humanityTV.setText(currentModel.getHumidity()+" %");
        windTV.setText(currentModel.getWind()+" m/s");
        descriptionTV.setText(currentModel.getDirection());
        currentDescriptionTV.setText(currentModel.getDescription());
        pressureTV.setText(currentModel.getPressure()+" hpa");
        int sunriseTM = currentModel.getSunRiseTime();
        String sunrise= new SimpleDateFormat("H:mm").format(new Date(sunriseTM*1000L));
        sunriseTV.setText(sunrise+" AM");
        int sunsetTM = currentModel.getSunSetTime();
        String sunset= new SimpleDateFormat("H:mm").format(new Date(sunsetTM*1000L));
        sunsetTV.setText(sunset+" PM");
    }
}
