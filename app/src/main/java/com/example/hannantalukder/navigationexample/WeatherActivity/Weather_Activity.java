package com.example.hannantalukder.navigationexample.WeatherActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hannantalukder.navigationexample.MainActivity;
import com.example.hannantalukder.navigationexample.R;
import com.example.hannantalukder.navigationexample.WeatherActivity.Adapter.Pager_Adapter;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.CitySelection;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.LocationInterface;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.MenuService;
import com.example.hannantalukder.navigationexample.WeatherActivity.Interface.NearByInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.List;

public class Weather_Activity extends AppCompatActivity implements MenuService,CitySelection,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,LocationInterface ,NearByInterface{

    private ViewPager viewPager;
    private Pager_Adapter pageAdapter;
    private TabLayout tabs;
    public static String dataType="metric";
    public static String queryCity="Dhaka";
    public static String queryAddress = "Dhanmondhi,Dhaka";

    /*Location*/
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    private double latitute;
    private double longitute;
    private Geocoder geocoder;
    private List<Address> addresses;
    /*---------*/
    public static String currentAddress;
    public static String currentcity;

    /*Temp Type*/
    public static String celcious="C";
    private MaterialSearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryCity = query;
                dataType = "metric";
                celcious = "C";
                viewPager.setAdapter(pageAdapter);
                tabs.setupWithViewPager(viewPager);
                Toast.makeText(Weather_Activity.this, ""+queryCity, Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        viewPager = (ViewPager) findViewById(R.id.pagerPG);
        tabs = (TabLayout) findViewById(R.id.tabs);

        pageAdapter = new Pager_Adapter(getSupportFragmentManager());
        tabs.setupWithViewPager(viewPager);

        /*--initialization*/
        locationMethod();

    }

    private void locationMethod() {
        geocoder = new Geocoder(this);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        inflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    public void getDataType(MenuItem item) {
        switch (item.getItemId()){
            case R.id.celsius:
                dataType = "metric";
                celcious = "C";
                viewPager.setAdapter(pageAdapter);
                tabs.setupWithViewPager(viewPager);
                break;
            case R.id.fahrenheit:
                dataType = "imperial";
                celcious = "F";
                viewPager.setAdapter(pageAdapter);
                tabs.setupWithViewPager(viewPager);
                break;
            case R.id.refresh:
                dataType = "metric";
                celcious = "C";
                viewPager.setAdapter(pageAdapter);
                tabs.setupWithViewPager(viewPager);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public String getData() {
        return dataType;
    }

    @Override
    public String getCelcious() {
        return celcious;
    }

    @Override
    public String getCity() {
        return queryCity;
    }

    @Override
    public String getlocationAddressLine() {
        return currentAddress;
    }

    @Override
    public String getlocationCity() {
        return currentcity;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create()
                .setInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient,
                locationRequest,
                this
        );
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        latitute = location.getLatitude();
        longitute = location.getLongitude();
        try {
            addresses = geocoder.getFromLocation(latitute,longitute,1);
            if(addresses.size() > 0)
            {
                String addressLine = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                currentAddress = addressLine;
                currentcity = city;
                queryAddress = (currentAddress+","+currentcity);
                Toast.makeText(this, ""+queryAddress, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLocationPosition() {
        return queryAddress;
    }
}