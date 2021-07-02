package com.example.gridviewexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    Context context;
    String[] countryNamesList;
    TypedArray imageList;

    ImageView imageViewFlag;
    TextView textViewCountryName;
    public GridAdapter(Context context, String[] countryNamesList, TypedArray imageList) {
        this.context = context;
        this.countryNamesList = countryNamesList;
        this.imageList = imageList;
    }
    @Override
    public int getCount() {
        return countryNamesList.length;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_layout
        , parent,false);

        imageViewFlag= (ImageView) view.findViewById(R.id.imageViewFlag);
        textViewCountryName= (TextView) view.findViewById(R.id.textViewCountryName);

        imageViewFlag.setImageResource(imageList.getResourceId(position,0));
        textViewCountryName.setText(countryNamesList[position]);
        return view;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
}
