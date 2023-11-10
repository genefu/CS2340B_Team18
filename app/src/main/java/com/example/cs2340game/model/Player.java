package com.example.cs2340game.model;

public class Player {
    private static Player playerInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;
    private Avatar avatar;


    // Constructor
    private Player(String id) {
        updateDifficultyStats(Model.Difficulty.MEDIUM);
        speed = 100;
        this.id = id;
        avatar = Avatar.getInstance("sprite1");
    }

    //Creates (if not already created) and returns the player instance
    public static Player getInstance(String id) {
        if (playerInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (playerInstance == null) {
                        playerInstance = new Player(id);
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

    // Updates the player stats based on the difficulty
    public void updateDifficultyStats(Model.Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            baseHealth = 100;
            baseStrength = 100;
            break;
        case HARD:
            baseHealth = 25;
            baseStrength = 25;
            break;
        default:
            baseHealth = 50;
            baseStrength = 50;
            break;
        }
    }

    // Getter for total health
    public int getHealth() {
        return baseHealth;
    }

    // Getter for total strength
    public int getStrength() {
        return baseStrength;
    }

    // Getter for player id
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

}
