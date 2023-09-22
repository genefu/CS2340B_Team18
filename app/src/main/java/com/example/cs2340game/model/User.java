package com.example.cs2340game.model;
import androidx.annotation.Nullable;

public class User {

    @Nullable
    private String playerName;
    private int score;
    private int difficulty; //TODO make this an ENUM

    // constructor to initialize
    // the variables
    public User(String playerName, int difficulty){
        this.playerName = playerName;
        this.score = 0;

    }

    //TODO make getters and setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
