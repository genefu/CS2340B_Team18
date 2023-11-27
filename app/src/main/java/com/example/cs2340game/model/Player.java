package com.example.cs2340game.model;

//import static androidx.core.content.ContextCompat.startActivity;

//import android.content.Intent;

import androidx.core.math.MathUtils;

//import com.example.cs2340game.views.EndView;
//import com.example.cs2340game.views.GameView;

public class Player {
    private static Player playerInstance;
    private Avatar avatar;
    private int health;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private int enemiesDefeated;

    // Constructor
    private Player(String name) {
        updateDifficultyStats(Model.Difficulty.MEDIUM);
        this.health = baseHealth;
        speed = 100;
        this.name = name;
        avatar = Avatar.getInstance("sprite1");
        enemiesDefeated = 0;
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

    public static Player getInstance() {
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

    //for use in JUnits
    public static void clearInstance() {
        Avatar.clearInstance();
        playerInstance = null;
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

    // Setter for player avatar
    public void setName(String name) {
        this.name = name;
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
