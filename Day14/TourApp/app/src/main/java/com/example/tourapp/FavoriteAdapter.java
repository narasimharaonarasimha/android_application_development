package com.example.tourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    Context context;
    List<City> favoriteCityList;

    public FavoriteAdapter(Context context, List<City> favoriteCityList) {
        this.context = context;
        this.favoriteCityList = favoriteCityList;
    }

    @NonNull
    @NotNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_favorite,
                parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull
                                             FavoriteAdapter.FavoriteViewHolder
                                             holder, int position) {
        City city=favoriteCityList.get(position);
        holder.setData(city, position);
    }

    @Override
    public int getItemCount() {
        return favoriteCityList.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder{
        TextView favoriteCityNameTextView;
        ImageView favoriteCityImage;
        private int currentPosition = -1;
        private City currentCity = null;
        public FavoriteViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            favoriteCityNameTextView=itemView.findViewById(R.id.txv_city_name);
            favoriteCityImage=itemView.findViewById(R.id.imv_city);
        }
        public void setData(City city, int position) {
            favoriteCityImage.setImageResource(city.getImageID());
            favoriteCityNameTextView.setText(city.getName());

            this.currentCity = city;
            this.currentPosition = position;
        }
    }
}
