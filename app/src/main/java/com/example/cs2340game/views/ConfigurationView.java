package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;

public class ConfigurationView extends AppCompatActivity {
    private ConfigurationViewModel viewModel;
    EditText nameSender;
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_view);
        nameSender = (EditText) findViewById(R.id.nameTextField);
        viewModel = new ConfigurationViewModel();
    }

    //Switches view to GameView
    public void toGameView(View view) {
        viewModel.setPlayerName(nameSender.getText().toString());
        //sendIntent.putExtra("nameSend",nameSender.getText().toString()); Obsolete
        startActivity(new Intent(this, GameView.class));
    }

    //registers a click on triangle
    public void onClickTriangle(View view) {
        Log.d("iwantdeath", "trangle clicked");
        viewModel.onButtonClicked(0);
    }

    //registers a click on square
    public void onClickSquare(View view) {
        viewModel.onButtonClicked(1);
    }

    //registers a click on circle
    public void onClickCircle(View view) {
        viewModel.onButtonClicked(2);
    }

}
