package com.example.tourapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    Context context;
    List<City> cityList;

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @NotNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d("CityAdapter", "onCreateViewHolder: view holder created");
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_city,
                parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CityAdapter.CityViewHolder holder,
                                 int position) {
        Log.d("CityAdapter", "onBindViewHolder: Position" + position);
        City city = cityList.get(position);
        holder.setData(city, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView cityNameTextView;
        ImageView cityImage, favoriteImage, deleteImage;
        Drawable icFavoriteBorderedImage, icFavoriteFilledImage;
        private int currentPosition = -1;
        private City currentCity = null;
        VacationSource vacationSource;
        public CityViewHolder(@NonNull @org.jetbrains.annotations.NotNull
                                      View itemView) {
            super(itemView);
            vacationSource=VacationSource.getInstance();
            cityNameTextView = itemView.findViewById(R.id.txv_city_name);
            cityImage = itemView.findViewById(R.id.imv_city);
            favoriteImage = itemView.findViewById(R.id.imv_favorite);
            deleteImage = itemView.findViewById(R.id.imv_delete);
            icFavoriteBorderedImage =
                    ResourcesCompat.getDrawable(context.getResources(),
                            R.drawable.ic_favorite_bordered, null);

            icFavoriteFilledImage =
                    ResourcesCompat.getDrawable(context.getResources(),
                            R.drawable.ic_favorite_filled, null);


        }

        public void setData(City city, int position) {
            cityImage.setImageResource(city.getImageID());
            cityNameTextView.setText(city.getName());
            if (city.isFavorite) {
                favoriteImage.setImageDrawable(icFavoriteFilledImage);
            } else {
                favoriteImage.setImageDrawable(icFavoriteBorderedImage);
            }
            this.currentCity = city;
            this.currentPosition = position;
        }

        public void setListeners() {
            favoriteImage.setOnClickListener(CityViewHolder.this);
            deleteImage.setOnClickListener(CityViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imv_delete:
                    deleteItem();
                    break;
                case R.id.imv_favorite:
                    addToFavorites();
                    break;
            }

        }

        private void deleteItem() {
            cityList.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition,cityList.size());
            vacationSource.favoriteCityList.remove(currentCity);
        }
        private void addToFavorites(){
            if(currentCity.isFavorite()){
                currentCity.isFavorite=false;
                favoriteImage.setImageDrawable(icFavoriteBorderedImage);
                vacationSource.favoriteCityList.remove(currentCity);
            }else{
                currentCity.isFavorite=true;
                favoriteImage.setImageDrawable(icFavoriteFilledImage);
                vacationSource.favoriteCityList.add(currentCity);
            }
        }
    }
}
