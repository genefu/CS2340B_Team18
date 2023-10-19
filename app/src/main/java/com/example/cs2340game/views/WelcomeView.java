package com.example.cs2340game.views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.viewmodels.WelcomeViewModel;

public class WelcomeView extends AppCompatActivity {
    private WelcomeViewModel viewModel;
    private MediaPlayer mediaPlayer;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("iwantdeath", "start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);
        mediaPlayer = MediaPlayer.create(this, R.raw.spooky);
        mediaPlayer.setVolume(200.0f, 200.0f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        viewModel = new WelcomeViewModel();
    }
    //Switches view to ConfigurationView
    public void toConfigurationView(View view) {
        startActivity(new Intent(WelcomeView.this, ConfigurationView.class));
    }

    //Exits the app
    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}