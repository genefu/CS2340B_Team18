package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;


//View Model for Welcome Screen
public class WelcomeViewModel extends BaseObservable {

    // constructor of ViewModel class
    public WelcomeViewModel() {
        // instantiating model
        Model.getInstance();
    }

    // actions to be performed
    // when user clicks a button
    public void onButtonClicked() {
        //TODO implement if button added
    }
}