package com.example.hannantalukder.navigationexample.WeatherActivity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hannantalukder.navigationexample.WeatherActivity.Fragment.CurrentWeather;
import com.example.hannantalukder.navigationexample.WeatherActivity.Fragment.ForecastWeather;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public class Pager_Adapter extends FragmentPagerAdapter {
    public Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new CurrentWeather();
            case 1:
                return new ForecastWeather();

        }
        return  null;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Current";
            case 1:
                return "Forecast";
        }
        return null;
    }
}

