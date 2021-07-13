package com.example.tourapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VacationSource {

    Integer[] imagesArray={
            R.drawable.thumb_4_1, R.drawable.thumb_4_2, R.drawable.thumb_4_3,
            R.drawable.thumb_4_4, R.drawable.thumb_4_7, R.drawable.thumb_4_8,
            R.drawable.thumb_4_0, R.drawable.thumb_7_0, R.drawable.thumb_7_1,
            R.drawable.thumb_7_2, R.drawable.thumb_4_5, R.drawable.thumb_4_6,
            R.drawable.thumb_1_0, R.drawable.thumb_1_1, R.drawable.thumb_1_2,
            R.drawable.thumb_1_3, R.drawable.thumb_1_4, R.drawable.thumb_1_5,
            R.drawable.thumb_1_6, R.drawable.thumb_1_7, R.drawable.thumb_1_8,
            R.drawable.thumb_1_9, R.drawable.thumb_2_0, R.drawable.thumb_2_1,
            R.drawable.thumb_4_9, R.drawable.thumb_5_0, R.drawable.thumb_5_1,
            R.drawable.thumb_5_2, R.drawable.thumb_5_3, R.drawable.thumb_5_4,
            R.drawable.thumb_5_5, R.drawable.thumb_5_6, R.drawable.thumb_5_7,
            R.drawable.thumb_5_8, R.drawable.thumb_5_9, R.drawable.thumb_6_0,
            R.drawable.thumb_6_1, R.drawable.thumb_6_2, R.drawable.thumb_6_3,
            R.drawable.thumb_6_4, R.drawable.thumb_6_5, R.drawable.thumb_6_6,
            R.drawable.thumb_6_7, R.drawable.thumb_6_8, R.drawable.thumb_6_9,
            R.drawable.thumb_2_2, R.drawable.thumb_2_3, R.drawable.thumb_2_4,
            R.drawable.thumb_2_5, R.drawable.thumb_2_6, R.drawable.thumb_2_7,
            R.drawable.thumb_2_8, R.drawable.thumb_2_9, R.drawable.thumb_3_0,
            R.drawable.thumb_3_1, R.drawable.thumb_3_2, R.drawable.thumb_3_3,
            R.drawable.thumb_3_4, R.drawable.thumb_3_5, R.drawable.thumb_3_6,
            R.drawable.thumb_3_7, R.drawable.thumb_3_8, R.drawable.thumb_3_9,
            R.drawable.thumb_7_3, R.drawable.thumb_7_4
    };

    List<Integer> images= Arrays.asList(imagesArray);
    private static VacationSource vacationSource=null;

    //singleton design pattern
    public static VacationSource getInstance(){
        if(vacationSource==null){
            Log.d("Vacation Source", "getInstance: Vacation Object Created");
            vacationSource= new VacationSource();
        }
        return vacationSource;
    }
    String[] cityNamesArray={
            "Prishtina", "Manchester", "Nottingham", "Portsmouth", "Kukes",
            "Tirana", "Vlora", "Durres", "Xian", "Shanghai",
            "Buffalo", "Boise", "Pittsburgh", "Scottsdale", "Boston",
            "Philly", "Darjeeling", "Jaipur", "DC", "Minneapolis",
            "New York City", "Denver", "Asheville", "Hull", "Liverpool",
            "Detroit", "Adelaide", "Tasmania", "Austin", "Kansas City", "Seattle",
            "Oakland", "Las Vegas", "New Orleans", "Bath", "Norwich",
            "Mumbai", "Cambridge", "London", "Bristol", "Brighton", "Durham",
            "San Diego", "Brooklyn", "Chicago", "Charleston", "Nashville",
            "York", "Stratford-upon-Avon", "Bournemouth", "Beijing",
            "Miami", "Portland", "Chengdu", "Hangzhou", "Suzhou", "Huangshan",
            "Hong Kong", "Cairns", "Perth", "Brisbane",
            "Budva", "Melbourne", "Great Barrier Reef", "Sydney"
    };
    List<String> cities= Arrays.asList(cityNamesArray);

    List<City> cityList=new ArrayList<>();
    List<City> favoriteCityList=new ArrayList<>();

    public List<City> getFavoriteCityList() {
        return favoriteCityList;
    }

    public List<City> getCityList() {
        int imageID;
        String cityName;
        for(int i=0;i<images.size();i++){
            imageID= images.get(i);
            cityName=cities.get(i);
            City city=new City(imageID,cityName, false);
            cityList.add(city);
        }
        return cityList;
    }

}
