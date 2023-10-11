package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

//View Model for Game Screen
public class GameViewModel extends BaseObservable {
    private Model model;
    private Player player;
    private int secondsPassed;
    //Constructor
    public GameViewModel() {
        this.model = Model.getInstance();
        player = model.getPlayer();
        secondsPassed = 0;
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
        return model.getScore();
    }

    public int getSeconds() {
        return secondsPassed;
    }

    //updates the view
    public void updateView() {

    }

    // Subtracts 1 point from score until it hits 0
    public void decrementScore() {
        int currentScore = model.getScore();
        if (currentScore > 0) {
            model.setScore(currentScore - 1);
        }
    }

    // Adds 1 second to secondsPassed
    public void incrementSecond() {
        secondsPassed++;
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
