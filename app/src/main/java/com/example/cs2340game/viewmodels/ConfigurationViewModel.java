package com.example.cs2340game.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    //Constructor
    public ConfigurationViewModel() {

    }

    // Sets the difficulty based on the difficulty buttons
    public void onDifficultyClicked(Model.Difficulty difficulty) {
        Log.d("iwantdeath", "view model triangle clicked");
        Model.setDifficulty(difficulty);
    }

    // Sets the player avatar based on the avatar buttons
    public void onSpriteClicked(String avatar) {
        Model.getPlayer().setAvatar(avatar);
    }
}
