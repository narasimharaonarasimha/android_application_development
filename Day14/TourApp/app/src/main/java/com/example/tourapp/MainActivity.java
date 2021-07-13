package com.example.tourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    NavController navController;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavHostFragment navHostFragment;

    AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.activity_main_toolbar);
        navHostFragment=(NavHostFragment)
                getSupportFragmentManager().
                findFragmentById(R.id.nav_host_frag);

        navController=navHostFragment.getNavController();
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        appBarConfiguration=new AppBarConfiguration.
                Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();
        NavigationUI.setupWithNavController(toolbar, navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isOpen()){
            drawerLayout.close();
        }else {
            super.onBackPressed();
        }
    }
}