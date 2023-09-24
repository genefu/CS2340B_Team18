package com.example.cs2340game.views;

import android.content.Intent;
import android.os.Bundle;
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
        Intent sendIntent = new Intent(this, GameView.class);
        sendIntent.putExtra("nameSend",nameSender.getText().toString());
        startActivity(sendIntent);
    }
}
