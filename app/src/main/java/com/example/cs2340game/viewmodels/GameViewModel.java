package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

//View Model for Game Screen
public class GameViewModel extends BaseObservable {
    private Model model;
    private Player player;
    private int secondsPassed;
    private int minutesPassed;
    private int hoursPassed;
    private int enemiesDefeated;
    //Constructor
    public GameViewModel() {
        this.model = Model.getInstance();
        player = Player.getInstance();
        secondsPassed = 0;
        enemiesDefeated = player.getEnemiesDefeated();
    }

    public void switchRoom() {

    }

    // Gets the total player health
    public int getHealth() {
        return player.getHealth();
    }

    // Gets the total player strength
    public int getStrength() {
        return player.getStrength();
    }

    // Gets the current player score
    public int getScore() {
        return model.getScore().getScoreValue();
    }

    public int getInvincibilityTime() {
        return player.getAvatar().getInvincibilityTime();
    }

    public void updatePlayerInvincibility() {
        player.getAvatar().updateInvincibility();
    }

    // Gets the string for the current time
    // Updates seconds, minutes, and hours to be correct
    public String getTime() {
        while (secondsPassed >= 60) {
            minutesPassed++;
            secondsPassed -= 60;
        }
        while (minutesPassed >= 60) {
            hoursPassed++;
            minutesPassed -= 60;
        }
        return (hoursPassed == 0 ? "" + minutesPassed : hoursPassed + ":"
                + String.format("%02d", minutesPassed)) + ":"
                + String.format("%02d", secondsPassed);
    }

    // Setter for all time variables for debug
    public void setTime(int secondsPassed, int minutesPassed, int hoursPassed) {
        this.secondsPassed = secondsPassed;
        this.minutesPassed = minutesPassed;
        this.hoursPassed = hoursPassed;
    }

    // Subtracts 1 point from score until it hits 0
    public void decrementScore() {
        int currentScore = model.getScore().getScoreValue();
        if (currentScore > 0) {
            model.getScore().setScoreValue(currentScore - 1);
        }
    }

    // Adds 1 second to secondsPassed
    public void incrementSecond() {
        secondsPassed++;
    }

    // Setter for enemies defeated
    public void setEnemiesDefeated(int enemiesDefeated) {//don't update this outside of GameView
        this.enemiesDefeated = enemiesDefeated;
    }

    // Getter for enemies defeated
    public int getEnemiesDefeated() {//don't use this outside of GameView
        return enemiesDefeated;
    }

    public void updateScore(int difference) {
        model.getScore().setScoreValue(model.getScore().getScoreValue() + (4 * difference)); //adds 4 points for each new enemy defeated
    }
}
