package com.example.cs2340game.model;

//import static androidx.core.content.ContextCompat.startActivity;

//import android.content.Intent;

import android.util.Log;

import androidx.core.math.MathUtils;

import com.example.cs2340game.model.Powerups.BasicPowerUp;
import com.example.cs2340game.model.Powerups.PowerUp;
import com.example.cs2340game.model.Powerups.PowerUpDecorator;
import com.example.cs2340game.model.Powerups.RangeUpDecorator;
import com.example.cs2340game.model.Powerups.ScoreUpDecorator;
import com.example.cs2340game.model.Powerups.SpeedUpDecorator;

//import com.example.cs2340game.views.EndView;
//import com.example.cs2340game.views.GameView;

public class Player {
    private static Player playerInstance;
    private Avatar avatar;
    private PowerUp powerUp;
    private int health;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int enemiesDefeated;
    private double scoreMultiplier;

    // Constructor
    private Player(String name) {
        updateDifficultyStats(Model.Difficulty.MEDIUM);
        powerUp = new BasicPowerUp(this);
        this.health = baseHealth;
        this.name = name;
        avatar = Avatar.getInstance("sprite1");
        enemiesDefeated = 0;
        scoreMultiplier = 1;
    }

    //Creates (if not already created) and returns the player instance
    public static Player getInstance(String name) {
        if (playerInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (playerInstance == null) {
                        playerInstance = new Player(name);
                    }
                }
            }
        }
        return playerInstance;
    }

    public static Player getInstance() { //gets instance of player
        if (playerInstance == null) {
            synchronized (Player.class) {
                if (playerInstance == null) {
                    throw new IllegalArgumentException(
                            "Player doesn't exist, needs a name parameter");
                }
            }
        }
        return playerInstance;
    }


    //for restarting game
    public void restartPlayer() {
        updateDifficultyStats(Model.getInstance().getDifficulty());
        health = baseHealth;
    }

    //for use in JUnits
    public static void clearInstance() {
        Avatar.clearInstance();
        playerInstance = null;
    }

    public void applyPowerUp(int type) {
        switch (type) {
            case 0: powerUp = new SpeedUpDecorator(powerUp); break;
            case 1: powerUp = new ScoreUpDecorator(powerUp); break;
            case 2: powerUp = new RangeUpDecorator(powerUp); break;
        }
        Log.d("powerup", "applying powerup up type " + type);
        powerUp.applyPowerUp();
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    // Updates the player stats based on the difficulty
    public void updateDifficultyStats(Model.Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            baseHealth = 100;
            baseDefense = 125;
            baseStrength = 100;
            break;
        case HARD:
            baseHealth = 25;
            baseDefense = 75;
            baseStrength = 25;
            break;
        default:
            baseHealth = 50;
            baseDefense = 100;
            baseStrength = 50;
            break;
        }
    }

    public void update() {
        avatar.updateInvincibility();
        powerUp.decrementTime();
    }

    public void removeHealth(int damage) {
        health = MathUtils.clamp(health - (int) (damage * (200 - baseDefense) / 100),
                0, baseHealth);
    }

    // Getter for total health
    public int getHealth() {
        return health;
    }

    // Getter for total strength
    public int getStrength() {
        return baseStrength;
    }

    // Getter for player name
    public String getName() {
        return name;
    }

    // Getter for player avatar
    public Avatar getAvatar() {
        return avatar;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    // Setter for player avatar
    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(double speed) {
        avatar.setSpeed(speed);
    }

    public void setScoreMultiplier(double scoreMultiplier) {
        this.scoreMultiplier = scoreMultiplier;
    }

    public double getScoreMultiplier() {
        return scoreMultiplier;
    }

    // Getter for enemies defeated
    public int getEnemiesDefeated() {
        return enemiesDefeated;
    }

    // Update defeated enemies (for score implementation)
    public void addDefeatedEnemies(int defeated) {
        enemiesDefeated += defeated;
    }
}
