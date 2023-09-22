package com.example.cs2340game.viewmodels;

import android.text.TextUtils;
import android.util.Patterns;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.model.User;

public class InitialConfigViewModel extends BaseObservable {

    // creating object of Model class
    private User model;

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
        model = new User("",-1);
    }

    // actions to be performed
    // when user clicks
    // the LOGIN button
    public void onButtonClicked() {

    }
}
