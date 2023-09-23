package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;

public class GameViewModel extends BaseObservable {

    // creating object of Model class
    private Model model;

    // constructor of ViewModel class
    public GameViewModel() {
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
