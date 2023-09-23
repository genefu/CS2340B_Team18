package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;

public class ConfigurationView extends AppCompatActivity {
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);
    }

    //Switches view to GameView
    public void toGameScreen(View view) {
        startActivity(new Intent(ConfigurationView.this, GameVIew.class));
    }
}
