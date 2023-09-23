package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;

public class WelcomeView extends AppCompatActivity { //added comments and standardized variable names
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);
    }
    //Switches view to ConfigurationView
    public void toConfigurationView(View view) {
        startActivity(new Intent(WelcomeView.this, ConfigurationView.class));
    }

    //Exits the app
    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}