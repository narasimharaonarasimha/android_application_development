package com.example.recyclerviewexample1;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder> {
    private String[] countryNamesList;
    private String[] detailsList;
    private TypedArray imageList;
    private Context context;


    public RecyclerAdapter(String[] countryNamesList, String[] detailsList,
                           TypedArray imageList, Context context) {
        this.countryNamesList = countryNamesList;
        this.detailsList = detailsList;
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerAdapter.CountryViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.getResourceId(position,0));
        holder.textViewCountryName.setText(countryNamesList[position]);
        holder.textViewDetails.setText(detailsList[position]);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, " You selected "
                        + countryNamesList[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryNamesList.length;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewCountryName, textViewDetails;
        CardView cardView;
        public CountryViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.profile_image);
            textViewCountryName=itemView.findViewById(R.id.textViewCountryName);
            textViewDetails=itemView.findViewById(R.id.textViewCountryDetails);
            cardView=itemView.findViewById(R.id.cardViewCountries);
        }
    }
}
