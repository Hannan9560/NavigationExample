package com.example.hannantalukder.navigationexample.PlacesActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hannantalukder.navigationexample.PlacesActivity.Fragment.PlacesFragment;
import com.example.hannantalukder.navigationexample.PlacesActivity.Interface.NearByPlacesNameInterface;
import com.example.hannantalukder.navigationexample.R;

public class NearByActivity extends AppCompatActivity implements NearByPlacesNameInterface {

    private double latitute;
    private double longitute;

    /*public static String queryArea = "Dhaka";*/
    public static String queryAreaPlaces = "ATM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PlacesFragment placesFragment = new PlacesFragment();
        ft.add(R.id.map_frag_Container,placesFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void fragmentInitialize() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PlacesFragment placesFragment = new PlacesFragment();
        ft.replace(R.id.map_frag_Container,placesFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void changePlaces(View view) {
        switch (view.getId())
        {
            case R.id.hospital_btn:
                queryAreaPlaces = "hospital";
                fragmentInitialize();
                break;
            case R.id.atm_btn:
                queryAreaPlaces = "atm";
                fragmentInitialize();
                break;
            case R.id.restaurant_btn:
                queryAreaPlaces = "restaurant";
                fragmentInitialize();
                break;
            case R.id.cafe_btn:
                queryAreaPlaces = "cafe";
                fragmentInitialize();
                break;
        }
    }

    @Override
    public String getQueryPlacesName() {
        return queryAreaPlaces;
    }
}
