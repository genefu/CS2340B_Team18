package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;

public class ConfigurationView extends AppCompatActivity {
    private ConfigurationViewModel viewModel;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);

        viewModel = new ConfigurationViewModel();
    }

    //Switches view to GameView
    public void toGameView(View view) {
        startActivity(new Intent(ConfigurationView.this, GameView.class));
    }

    //registers a click on triangle
    public void onClickTriangle() {
        viewModel.onButtonClicked(0);
    }

    //registers a click on square
    public void onClickSquare() {
        viewModel.onButtonClicked(1);
    }

    //registers a click on circle
    public void onClickCircle() {
        viewModel.onButtonClicked(2);
    }

}
