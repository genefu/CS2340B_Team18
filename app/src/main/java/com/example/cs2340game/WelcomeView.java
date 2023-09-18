package com.example.cs2340game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeView extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);
    }

    public void configScreen(View view) {
        startActivity(new Intent(WelcomeView.this, InitialConfigView.class));
    }
}