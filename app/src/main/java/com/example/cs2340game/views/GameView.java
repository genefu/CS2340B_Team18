package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.GameViewModel;

public class GameView extends AppCompatActivity {
    private GameViewModel viewModel;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);

        viewModel = new GameViewModel();
    }

    //Switches view to EndView
    public void toEndView(View view) {
        startActivity(new Intent(GameView.this, EndView.class));
    }
}