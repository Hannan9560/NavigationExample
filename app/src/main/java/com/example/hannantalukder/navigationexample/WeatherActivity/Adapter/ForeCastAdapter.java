package com.example.hannantalukder.navigationexample.WeatherActivity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hannantalukder.navigationexample.R;
import com.example.hannantalukder.navigationexample.WeatherActivity.ModelClass.WeatherModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Hannan Talukder on 5/5/2017.
 */

public class ForeCastAdapter extends ArrayAdapter<WeatherModelClass> {
    private Context context;
    private ArrayList<WeatherModelClass> weatherModelClasses;
    private boolean isFormatCelsius;

    public ForeCastAdapter(@NonNull Context context, ArrayList<WeatherModelClass> weatherModelClasses, boolean isFormatCelsius) {
        super(context, R.layout.forecast_listview, weatherModelClasses);
        this.context = context;
        this.weatherModelClasses = weatherModelClasses;
        this.isFormatCelsius = isFormatCelsius;
    }

    class ViewHolder{
        TextView dayTV, dateTV, tempHighTV, tempLowTV, descriptionTV, speedTV, cloudTV, pressureTV;
        ImageView weatherIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView ==null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.forecast_listview,parent,false);

            holder.dayTV = (TextView) convertView.findViewById(R.id.forecastDayNameTv);
            holder.dateTV = (TextView) convertView.findViewById(R.id.forecastDateTv);
            holder.tempHighTV = (TextView) convertView.findViewById(R.id.forecastDayHighTempTv);
            holder.tempLowTV = (TextView) convertView.findViewById(R.id.forecastDayLowTempTv);
            holder.descriptionTV = (TextView) convertView.findViewById(R.id.forecastDescriptionTV);
            holder.speedTV = (TextView) convertView.findViewById(R.id.speedTV);
            holder.cloudTV = (TextView) convertView.findViewById(R.id.cloudTV);
            holder.pressureTV = (TextView) convertView.findViewById(R.id.pressureTV);
            holder.weatherIcon = (ImageView) convertView.findViewById(R.id.forecastIconTv);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.dayTV.setText(weatherModelClasses.get(position).getWeatherDay());
        holder.dateTV.setText(weatherModelClasses.get(position).getWeatherDate());
        holder.descriptionTV.setText(weatherModelClasses.get(position).getDescription());
        holder.speedTV.setText(String.valueOf(weatherModelClasses.get(position).getSpeed())+" m/s" );
        holder.cloudTV.setText("Clouds: "+String.valueOf(weatherModelClasses.get(position).getCloud())+ " %" );
        holder.pressureTV.setText(String.valueOf(weatherModelClasses.get(position).getPressure())+" hpa");
        holder.tempHighTV.setText(String.valueOf(weatherModelClasses.get(position).getHighTemp())+"°");
        holder.tempLowTV.setText(String.valueOf(weatherModelClasses.get(position).getLowTemp())+"°");
        Picasso.with(getContext()).load("http://openweathermap.org/data/2.5/forecast/img/w/"+weatherModelClasses.get(position).getWeatherIcon()+".png").into(holder.weatherIcon);
        return convertView;
    }
}

