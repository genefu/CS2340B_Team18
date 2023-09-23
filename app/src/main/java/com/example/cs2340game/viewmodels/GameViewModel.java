package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;

public class GameViewModel extends BaseObservable {
    private Model model;

    //Constructor
    public GameViewModel() {
        model = new Model("");
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}
