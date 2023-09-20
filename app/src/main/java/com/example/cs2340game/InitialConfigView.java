package com.example.cs2340game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InitialConfigView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);
    }

    public void gameScreen(View view) {
        startActivity(new Intent(InitialConfigView.this, GameActivity.class));
    }
}
