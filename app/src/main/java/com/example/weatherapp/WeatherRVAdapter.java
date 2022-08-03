package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRVAdapter> weatherRVAdapterArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVAdapter> weatherRVAdapterArrayList) {
        this.context = context;
        this.weatherRVAdapterArrayList = weatherRVAdapterArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRVModal modal = weatherRVAdapterArrayList.get(position);
        holder.temperatureTV.setText(modal.getTemperature()+"°C");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.ConditionIV);
        holder.windTV.setText(modal.getWindSpeed()+"km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try {
            Date t = input.parse(modal.getTime());
            holder.timeTV.setText(output.format(t));
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

        return weatherRVAdapterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView windTV,temperatureTV,timeTV;
        private ImageView ConditionIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTV = itemView.findViewById(R.id.idTVTemperature);
            timeTV = itemView.findViewById(R.id.idTVTime);
            ConditionIV = itemView.findViewById(R.id.idIVCondition);
        }
    }
}
