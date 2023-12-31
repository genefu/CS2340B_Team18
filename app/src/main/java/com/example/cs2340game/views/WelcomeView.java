package com.example.cs2340game.views;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.media.MediaPlayer;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.viewmodels.WelcomeViewModel;

public class WelcomeView extends AppCompatActivity {
    private WelcomeViewModel viewModel;
    private MediaPlayer mediaPlayer;
    private int orientation;
    //Displays the viewds
    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("iwantdeath", "start");
        super.onCreate(savedInstanceState);

        orientation = this.getResources().getConfiguration().orientation;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.welcome_view);
        mediaPlayer = MediaPlayer.create(this, R.raw.spooky);
        mediaPlayer.setVolume(200.0f, 200.0f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        viewModel = new WelcomeViewModel();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Log.d("collision", width + " " + height);
        Model model = Model.getInstance();
        model.setScreenWidth(width);
        model.setScreenHeight(height);
    }
    //Switches view to ConfigurationView
    public void toConfigurationView(View view) {
        mediaPlayer.release();
        startActivity(new Intent(WelcomeView.this, ConfigurationView.class));
    }

    //Exits the app
    public void exitApplication(View view) {
        finish();
        System.exit(0);
    }
}