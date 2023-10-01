package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

//View Model for Game Screen
public class GameViewModel extends BaseObservable {
    private Player player;
    //Constructor
    public GameViewModel() {
        player = Model.getPlayer();
    }

    // Gets the total player health
    public int getHealth() {
        return player.getHealth();
    }

    // Gets the total player strength
    public int getStrength() {
        return player.getStrength();
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
