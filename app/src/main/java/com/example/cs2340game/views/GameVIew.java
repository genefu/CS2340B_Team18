package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;

public class GameVIew extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
    }

    public void endScreen(View view) {
        startActivity(new Intent(GameVIew.this, EndView.class));
    }
}