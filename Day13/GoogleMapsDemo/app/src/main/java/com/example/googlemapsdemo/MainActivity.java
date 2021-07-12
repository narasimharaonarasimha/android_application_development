package com.example.googlemapsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity
implements OnMapReadyCallback {
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment= (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull @org.jetbrains.annotations.NotNull
                                       GoogleMap googleMap) {
        map=googleMap;
        LatLng prishtina=new LatLng(42.660948538119165,
                21.158973913884065);
        map.addMarker(new MarkerOptions().position(prishtina)
        .title("New Born - Prishtina"));
        map.moveCamera(CameraUpdateFactory.newLatLng(prishtina));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));


    }
}