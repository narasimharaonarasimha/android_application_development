package com.example.cafeapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cafeapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String message;
    static final String extra_message="extra_message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,
                        OrderActivity.class);
                intent.putExtra(extra_message, message);
                startActivity(intent);
            }
        });


    }
    public void showDonutOrder(View view){
        message=getString(R.string.donut_order_message);
        displaySnackBar(message);
        //displayToast(getString(R.string.donut_order_message));
    }
    private void displaySnackBar(String message) {
        CoordinatorLayout coordinatorLayout= (CoordinatorLayout)
                findViewById(R.id.coordinatorLayout);
        Snackbar.make(coordinatorLayout,message, Snackbar.LENGTH_LONG)
        .show();

    }

    public void showFroyoOrder(View view){
        message=getString(R.string.froyo_order_message);
        displayToast(message);
    }
    public void showIceCreamOrder(View view){
        message=getString(R.string.icecream_order_message);
        displayToast(message);
    }
    public void displayToast(String message){
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order) {
            displayToast(getString(R.string.action_order_message));
            return true;
        }
        if (id == R.id.action_status) {
            displayToast(getString(R.string.action_status_message));
            return true;
        }
        if (id == R.id.action_favorites) {
            displayToast(getString(R.string.action_favorites_message));
            return true;
        }
        if (id == R.id.action_contact) {
            displayToast(getString(R.string.action_contact_message));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}