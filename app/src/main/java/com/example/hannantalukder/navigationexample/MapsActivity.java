package com.example.hannantalukder.navigationexample;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.hannantalukder.navigationexample.Map_Fragments.DirectionFragment;
import com.example.hannantalukder.navigationexample.Map_Fragments.MapsFragment;
import com.example.hannantalukder.navigationexample.PlacesActivity.NearByActivity;

public class MapsActivity extends AppCompatActivity  {

    private double lat;
    private double lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        lat = getIntent().getDoubleExtra("lt",1);
        lon = getIntent().getDoubleExtra("ln",1);
        Toast.makeText(this, "g"+lat+lon, Toast.LENGTH_SHORT).show();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MapsFragment mapsFragment = new MapsFragment();
        ft.add(R.id.map_frag_Container, mapsFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void changePosition(View view) {
        Fragment fragment = null;
        switch (view.getId())
        {
            case  R.id.direction_btn:
                fragment = new DirectionFragment();
                break;
            case R.id.searchmap_btn:
                fragment = new MapsFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.map_frag_Container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void homeActivity(View view) {
        startActivity(new Intent(MapsActivity.this, MainActivity.class));
    }

    public void changePlaces(View view) {
        Intent intent = new Intent(MapsActivity.this, NearByActivity.class);
        startActivity(intent);
    }
}
