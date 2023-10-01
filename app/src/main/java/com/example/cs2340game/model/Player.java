package com.example.cs2340game.model;

public class Player {
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private String sprite;
    private String avatar;


    // Constructor
    public Player(String name) {
        updateDifficultyStats();
        speed = 100;
        this.name = name;
        avatar = "sprite1";
    }

    // Updates the player stats based on the difficulty
    public void updateDifficultyStats() {
        switch (Model.getDifficulty()) {
            case 0: baseHealth = 100;
                baseStrength = 100;
                break;
            case 1: baseHealth = 50;
                baseStrength = 50;
                break;
            case 2: baseHealth = 25;
                baseStrength = 25;
                break;
            default: baseHealth = 50;
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
