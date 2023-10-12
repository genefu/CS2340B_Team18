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

public class GameView extends AppCompatActivity implements GameTimer.TimerListener {
    private Model model;
    private TextView nameTextView;
    private TextView healthTextView;
    private TextView strengthTextView;
    private TextView scoreTextView;
    private TextView timeTextView;
    private ImageView playerSprite;
    private GameViewModel viewModel;
    private GameTimer gameTimer;
    private int tickOffset;
    private String date;

    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        this.model = Model.getInstance();

        viewModel = new GameViewModel();
        nameTextView = findViewById(R.id.nameReceiver);
        nameTextView.setText(model.getPlayerName());
        healthTextView = findViewById(R.id.HealthStat);
        healthTextView.setText("Health: " + Integer.toString(viewModel.getHealth()));
        strengthTextView = findViewById(R.id.StrengthStat);
        strengthTextView.setText("Strength: " + Integer.toString(viewModel.getStrength()));
        scoreTextView = findViewById(R.id.ScoreText);
        scoreTextView.setText("Score: " + Integer.toString(viewModel.getScore()));
        timeTextView = findViewById(R.id.TimeText);
        timeTextView.setText("Time: " + viewModel.getTime());
        playerSprite = findViewById(R.id.player_sprite);
        int id = this.getResources().getIdentifier(model.getPlayer().getAvatar(),
                "drawable", this.getPackageName());
        playerSprite.setImageResource(id);

        gameTimer = new GameTimer(this);
        tickOffset = gameTimer.getTicks() % 40;
    }

    //switches view to second game screen
    public void toGameView2(View view) {
        setContentView(R.layout.game_view2);
        nameTextView = findViewById(R.id.nameReceiver);
        nameTextView.setText(model.getPlayerName());
        healthTextView = findViewById(R.id.HealthStat);
        healthTextView.setText("Health: " + Integer.toString(viewModel.getHealth()));
        strengthTextView = findViewById(R.id.StrengthStat);
        strengthTextView.setText("Strength: " + Integer.toString(viewModel.getStrength()));
        scoreTextView = findViewById(R.id.ScoreText);
        scoreTextView.setText("Score: " + Integer.toString(viewModel.getScore()));
        timeTextView = findViewById(R.id.TimeText);
        timeTextView.setText("Time: " + viewModel.getTime());
        playerSprite = findViewById(R.id.player_sprite);
        int id = this.getResources().getIdentifier(model.getPlayer().getAvatar(),
                "drawable", this.getPackageName());
        playerSprite.setImageResource(id);
    }

    //switches view to third game screen
    public void toGameView3(View view) {
        setContentView(R.layout.game_view3);
        nameTextView = findViewById(R.id.nameReceiver);
        nameTextView.setText(model.getPlayerName());
        healthTextView = findViewById(R.id.HealthStat);
        healthTextView.setText("Health: " + Integer.toString(viewModel.getHealth()));
        strengthTextView = findViewById(R.id.StrengthStat);
        strengthTextView.setText("Strength: " + Integer.toString(viewModel.getStrength()));
        scoreTextView = findViewById(R.id.ScoreText);
        scoreTextView.setText("Score: " + Integer.toString(viewModel.getScore()));
        timeTextView = findViewById(R.id.TimeText);
        timeTextView.setText("Time: " + viewModel.getTime());
        playerSprite = findViewById(R.id.player_sprite);
        int id = this.getResources().getIdentifier(model.getPlayer().getAvatar(),
                "drawable", this.getPackageName());
        playerSprite.setImageResource(id);
    }

    //Switches view to EndView
    public void toEndView(View view) {
        startActivity(new Intent(GameView.this, EndView.class));
        //record score when button is pressed (will later change to when player completes level)
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        date = dateFormat.format(Calendar.getInstance().getTime());
        model.updateLeaderboard(new Score(model.getPlayerName(), viewModel.getScore(), date));
    }

    @Override
    public void onTimerUpdate(int ticks) {
        Log.d("iwantdeath", "gameTicks: " + ticks);
        //viewModel.updateView();
        if (ticks % 20 - tickOffset % 20 == 0) { //every half second
            viewModel.decrementScore();
            if (ticks % 40 - tickOffset == 0) { //every second
                viewModel.incrementSecond();
            }
        }
        scoreTextView.setText("Score: " + Integer.toString(viewModel.getScore()));
        timeTextView.setText("Time: " + viewModel.getTime());
    }
}