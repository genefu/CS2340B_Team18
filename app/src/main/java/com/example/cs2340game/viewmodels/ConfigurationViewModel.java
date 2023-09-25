package com.example.cs2340game.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.BR;
import com.example.cs2340game.model.Model;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    //@Bindable
    private String playerName;

    public String getPlayerName() {
        return this.playerName;
    }

    //Setter for playerName in Model
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        Model.setPlayerName(playerName);
        //notifyPropertyChanged(BR.playerName); //TODO FIX, updates the view using binded data
        //TODO connect to text input
    }

    //Constructor
    public ConfigurationViewModel() {

    }

    // Sets the difficulty based on character sprite choice
    public void onButtonClicked(int difficulty) {
        Log.d("iwantdeath", "view model triangle clicked");
        Model.setDifficulty(difficulty);
    }
}
