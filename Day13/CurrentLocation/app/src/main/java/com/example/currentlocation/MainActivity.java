package com.example.currentlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends FragmentActivity implements
        OnMapReadyCallback {
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                currentLocation=location;
                Toast.makeText(getApplicationContext(),
                        "Latitude: " + currentLocation.getLatitude() +
                        "Longitude: " + currentLocation.getLongitude(),
                        Toast.LENGTH_SHORT).show();
                SupportMapFragment mapFragment=
                        (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MainActivity.this);
            }
        });

    }

    @Override
    public void onMapReady(@NonNull @org.jetbrains.annotations.NotNull
                                       GoogleMap googleMap) {
        LatLng myLocation=new LatLng(currentLocation.getLatitude(),
                currentLocation.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(myLocation)
                .title("I am here");
        //googleMap.animateCamera(CameraUpdateFactory.newLatLng(myLocation));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,15));
        googleMap.addMarker(markerOptions);
    }
}