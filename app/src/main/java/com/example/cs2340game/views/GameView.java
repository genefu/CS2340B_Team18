package com.example.cs2340game.views;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.icu.text.DateFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Score;
import com.example.cs2340game.model.SprintStrategy;
import com.example.cs2340game.model.StandardVectors;
import com.example.cs2340game.model.WalkStrategy;
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
    private ImageView gameView;
    private GameRender gameRender;
    private GameTimer gameTimer;
    private int tickOffset;
    private String date;
    private int currentRoom;
    private Avatar avatar;

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
        avatar = model.getPlayer().getAvatar();
        int id = this.getResources().getIdentifier(avatar.getSprite(),
                "drawable", this.getPackageName());
        playerSprite.setImageResource(id);

        gameView = (ImageView) findViewById(R.id.tileSet);
        currentRoom = 1;
        gameRender = new GameRender(gameView, currentRoom, this);
        //gameView.setImageBitmap(tileSet.getTileSet());

        gameTimer = new GameTimer(this);
        tickOffset = gameTimer.getTicks() % 40;
    }

    //switches view to second game screen
    public void switchRoom() {
        currentRoom++;
        if (currentRoom == 4) {
            toEndView();
        } else {
            //gameRender = new GameRender(gameView, currentRoom + "", this);
            gameRender.getMapLayout().setScreen(currentRoom);
            //Log.d("bruh", currentRoom + ": room");
        }

    }

    //Switches view to EndView
    public void toEndView() {
        //record score when button is pressed (will later change to when player completes level)
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        date = dateFormat.format(Calendar.getInstance().getTime());
        model.updateLeaderboard(new Score(model.getPlayerName(), viewModel.getScore(), date));
        model.getScore().setPlayerName(model.getPlayerName());
        model.getScore().setDateTime(date);
        startActivity(new Intent(GameView.this, EndView.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("keyPress", keyCode + " down");
        boolean out = super.onKeyUp(keyCode, event);
        if (keyCode == KeyEvent.KEYCODE_W) {
            avatar.applyVector(StandardVectors.UP_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_A) {
            avatar.applyVector(StandardVectors.LEFT_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_S) {
            avatar.applyVector(StandardVectors.DOWN_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_D) {
            avatar.applyVector(StandardVectors.RIGHT_VECTOR);
            out = true;
        }
        if (event.isShiftPressed()) {
            avatar.setMovementStrategy(new SprintStrategy());
        } else {
            avatar.setMovementStrategy(new WalkStrategy());
        }
        return out;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean out = super.onKeyUp(keyCode, event);
        //Log.d("keyPress", keyCode + " up");
        if (keyCode == KeyEvent.KEYCODE_W) {
            avatar.removeVector(StandardVectors.UP_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_A) {
            avatar.removeVector(StandardVectors.LEFT_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_S) {
            avatar.removeVector(StandardVectors.DOWN_VECTOR);
            out = true;
        }
        if (keyCode == KeyEvent.KEYCODE_D) {
            avatar.removeVector(StandardVectors.RIGHT_VECTOR);
            out = true;
        }
        if (avatar.isOnExit()) {
            switchRoom();
        }
        return out;

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
        //avatar.updatePosition();
        gameRender.refreshScreen();
    }
}