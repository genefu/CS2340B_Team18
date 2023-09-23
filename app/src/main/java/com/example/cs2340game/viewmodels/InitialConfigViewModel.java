package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.model.Model;

public class InitialConfigViewModel extends BaseObservable {
    private Model model;

    //Setter for playerName in Model
    public void setPlayerName(String playerName) {
        model.setPlayerName(playerName);
        //notifyPropertyChanged(BR.playerName); TODO FIX, updates the view using binded data
        //TODO connect to text input
    }


    //Constructor
    public InitialConfigViewModel() {
        // instantiating object of
        // model class
        model = new Model("");
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
