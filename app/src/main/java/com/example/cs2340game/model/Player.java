package com.example.cs2340game.model;

public class Player {
    private static Player playerInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private String sprite;
    private String avatar;


    // Constructor
    private Player(String name) {
        updateDifficultyStats(Model.Difficulty.MEDIUM);
        speed = 100;
        this.name = name;
        avatar = "sprite1";
    }

    //Creates (if not already created) and returns the player instance
    public static Player getInstance(String name) {
        return playerInstance == null? playerInstance = new Player(name): playerInstance;
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

    // Getter for player name
    public String getName() {
        return name;
    }

    // Getter for player avatar
    public String getAvatar() {
        return avatar;
    }

    // Setter for player avatar
    public void setName(String name) {
        this.name = name;
    }

    // Setter for player avatar
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
