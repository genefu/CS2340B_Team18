package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;

public class EndView extends AppCompatActivity {
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_view);
    }

    //Switches view to WelcomeView
    public void toWelcomeView(View view) {
        startActivity(new Intent(EndView.this, WelcomeView.class));
    }
}
