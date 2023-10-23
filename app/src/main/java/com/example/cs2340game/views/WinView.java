package com.example.cs2340game.views;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageView;
//import android.widget.LinearLayout;
import android.widget.TextView;
import android.icu.text.DateFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Score;
import com.example.cs2340game.viewmodels.GameViewModel;

public class WinView extends AppCompatActivity {
    private Model model;
    private TextView nameTextView;
    private TextView healthTextView;
    private TextView strengthTextView;
    private TextView scoreTextView;
    private TextView timeTextView;
    private ImageView playerSprite;
    private GameViewModel viewModel;
    private ImageView gameView;
    private TileMap tileSet;
    private GameTimer gameTimer;
    private int tickOffset;
    private String date;
    private int currentRoom;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_view);
        this.model = Model.getInstance();
    }

    //Switches view to EndView
    public void toEndView(View view) {
        //record score when button is pressed (will later change to when player completes level)
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        date = dateFormat.format(Calendar.getInstance().getTime());
        model.updateLeaderboard(new Score(model.getPlayerName(), viewModel.getScore(), date));
        model.getScore().setPlayerName(model.getPlayerName());
        model.getScore().setDateTime(date);
        startActivity(new Intent(WinView.this, EndView.class));
    }

}