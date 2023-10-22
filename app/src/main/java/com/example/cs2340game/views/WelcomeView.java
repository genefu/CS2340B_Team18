package com.example.cs2340game.views;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.viewmodels.WelcomeViewModel;

public class WelcomeView extends AppCompatActivity {
    private WelcomeViewModel viewModel;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("iwantdeath", "start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_view);

        viewModel = new WelcomeViewModel();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Model model = Model.getInstance();
        model.setScreenWidth(width);
        model.setScreenHeight(height);
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