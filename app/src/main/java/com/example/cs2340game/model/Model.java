package com.example.cs2340game.model;
import androidx.annotation.Nullable;

public class Model {

    @Nullable
    private String playerName;
    private int score;
    private int difficulty; //TODO make this an ENUM (0 = easy, 1 = medium)
    //TODO probably have an enum for different weapon types

    // constructor to initialize
    // the variables
    public Model(String playerName) {
        this.playerName = playerName;
        this.difficulty = 0;
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
