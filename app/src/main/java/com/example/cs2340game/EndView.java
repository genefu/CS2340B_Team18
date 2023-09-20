package com.example.cs2340game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EndView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_view);
    }

    public void welcomeScreen(View view) {
        startActivity(new Intent(EndView.this, WelcomeView.class));
    }
}
