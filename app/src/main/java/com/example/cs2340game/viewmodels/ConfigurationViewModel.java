package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    //Setter for playerName in Model
    public void setPlayerName(String playerName) {
        Model.setPlayerName(playerName);
        //notifyPropertyChanged(BR.playerName); TODO FIX, updates the view using binded data
        //TODO connect to text input
    }


    //Constructor
    public ConfigurationViewModel() {

    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
