package com.example.cs2340game.model;
import androidx.annotation.Nullable;

public class Model {

    @Nullable
    private String playerName;
    private int score;
    private int difficulty; //TODO make this an ENUM (0 = easy, 1 = medium)

    //TODO enum variable for different weapon types

    //Constructor
    public Model(String playerName) {
        this.playerName = playerName;
        this.difficulty = 0;
        this.score = 0;
    }

    //TODO make getters and setters

    //Getter for playerName
    public String getPlayerName() {
        return playerName;
    }

    //Setter for playerName
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
