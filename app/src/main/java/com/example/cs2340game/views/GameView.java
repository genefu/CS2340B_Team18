package com.example.cs2340game.views;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.icu.text.DateFormat;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Powerups.BasicPowerUp;
import com.example.cs2340game.model.Score;
import com.example.cs2340game.model.MovementStrategies.SprintStrategy;
import com.example.cs2340game.model.MovementStrategies.StandardVectors;
import com.example.cs2340game.model.MovementStrategies.WalkStrategy;
import com.example.cs2340game.viewmodels.GameViewModel;

import java.util.TreeSet;

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
    private Player player;
    private MediaPlayer mediaPlayer;
    private MediaPlayer enemyKill;
    private MediaPlayer takeDamage;


    //Displays the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_view);
        this.model = Model.getInstance();
        player = Player.getInstance();
        mediaPlayer = MediaPlayer.create(this, R.raw.caretaker);
        enemyKill = MediaPlayer.create(this, R.raw.enemykill);
        takeDamage = MediaPlayer.create(this, R.raw.takedamage);
        takeDamage.setVolume(200.0f, 200.0f);
        enemyKill.setVolume(200.0f, 200.0f);
        mediaPlayer.setVolume(200.0f, 200.0f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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
        avatar = player.getAvatar();
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
        if (currentRoom == 2) {
            mediaPlayer = MediaPlayer.create(this, R.raw.burningmemory);
            mediaPlayer.setVolume(200.0f, 200.0f);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        if (currentRoom == 3) {
            mediaPlayer = MediaPlayer.create(this, R.raw.stage4);
            mediaPlayer.setVolume(200.0f, 200.0f);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
        player.getPowerUp().removePowerUp();
        player.setPowerUp(new BasicPowerUp(Player.getInstance()));
        avatar.setSprite(avatar.getSprite().substring(0, 7));
        if (currentRoom == 4) {
            toWinView();
        } else {
            //gameRender = new GameRender(gameView, currentRoom + "", this);
            gameRender.getMapLayout().setScreen(currentRoom);
            //Log.d("bruh", currentRoom + ": room");
        }

    }

    //Switches view to EndView
    public void toWinView() {
        gameTimer.stopTimer();
        mediaPlayer.release();
        //record score when button is pressed (will later change to when player completes level)
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        date = dateFormat.format(Calendar.getInstance().getTime());
        model.updateLeaderboard(new Score(model.getPlayerName(), viewModel.getScore(), date));
        model.getScore().setPlayerName(model.getPlayerName());
        model.getScore().setDateTime(date);
        startActivity(new Intent(GameView.this, WinView.class));
    }

    public void toLoseView() {
        mediaPlayer.release();
        //record score when button is pressed (will later change to when player completes level)
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        date = dateFormat.format(Calendar.getInstance().getTime());
        model.updateLeaderboard(new Score(model.getPlayerName(), viewModel.getScore(), date));
        model.getScore().setPlayerName(model.getPlayerName());
        model.getScore().setDateTime(date);
        startActivity(new Intent(GameView.this, LoseView.class));
    }

    public void killEnemies(TreeSet<Enemy> enemies) {
        for (Enemy e: enemies) {
            if (e.getDistance(avatar.getPosX(), avatar.getPosY())
                    < (86.0 / 3) * player.getRange()) {
                enemyKill.start();
                model.removeEnemy(e.getID());
                viewModel.updateScore(1);
            }
        }
    }

    //Applies pos vector depending on key pressed
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
        if (keyCode == KeyEvent.KEYCODE_F) {
            TreeSet<Enemy> enemies = model.getRenderedEnemies();
            killEnemies(enemies);
            viewModel.setAttackCircle("attack_circle_filled");
            Log.d("attack", "presumably killed enemies");
        }
        if (event.isShiftPressed()) {
            avatar.setMovementStrategy(new SprintStrategy());
        } else {
            avatar.setMovementStrategy(new WalkStrategy());
        }
        return out;
    }

    //applies pos vector when key is not pressed
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


    //Switches to endview once players health reaches 0
    @Override
    public void onTimerUpdate(int ticks) {
        Log.d("iwantdeath", "invincibility:" + viewModel.getInvincibilityTime());
        viewModel.updatePlayer();
        if (ticks % 20 - tickOffset % 20 == 0) { //every half second
            viewModel.decrementScore();
            if (ticks % 40 - tickOffset == 0) { //every second
                viewModel.incrementSecond();
            }
        }
        viewModel.setEnemiesDefeated(player.getEnemiesDefeated());
        //update enemies defeated in viewmodel
        healthTextView.setText("Health: " + viewModel.getHealth());
        scoreTextView.setText("Score: " + Integer.toString(viewModel.getScore()));
        timeTextView.setText("Time: " + viewModel.getTime());
        //avatar.updatePosition();
        if (avatar.checkEnemyCollision(model.getRenderedEnemies())) {
            takeDamage.start();
        }
        int collectedPowerUp = avatar.checkPowerUpCollision(model.getRenderedPowerUps());
        if (collectedPowerUp != -1) {
            model.removePowerUp(1);
            player.applyPowerUp(collectedPowerUp);
            //Log.d("powerup", "applied powerup");
        }
        Log.d("Enemies", model.getRenderedEnemies().toString());
        if (viewModel.getHealth() <= 0) {
            Log.d("help", "going to end screen");
            gameTimer.stopTimer();
            toLoseView();
            return;
        }
        gameRender.refreshScreen();
        if (model.getAttackCircle().equals("attack_circle_filled")) {
            model.setAttackCircle("attack_circle");
        }
    }

    public void pausePlay(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }
}