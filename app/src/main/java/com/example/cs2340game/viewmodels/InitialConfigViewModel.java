package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.model.Model;

public class InitialConfigViewModel extends BaseObservable {

    // creating object of Model class
    private Model model;

    // getter and setter methods
    // for playerName variable
    @Bindable
    public String getPlayerName() {
        return model.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        model.setPlayerName(playerName);
        //notifyPropertyChanged(BR.userEmail); FIX
    }


    // constructor of ViewModel class
    public InitialConfigViewModel() {
        // instantiating object of
        // model class
        model = new Model("");
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO
    }
}
