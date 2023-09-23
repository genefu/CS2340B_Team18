package com.example.cs2340game.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.cs2340game.model.Model;

public class WelcomeViewModel extends BaseObservable {

    // creating object of Model class
    private Model model;

    // constructor of ViewModel class
    public WelcomeViewModel() {
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
