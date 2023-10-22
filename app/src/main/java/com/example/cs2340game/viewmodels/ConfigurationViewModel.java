package com.example.cs2340game.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Model;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    private Model model;
    //Constructor
    public ConfigurationViewModel() {
        this.model = Model.getInstance();
    }

    // Sets the difficulty based on the difficulty buttons
    public void onDifficultyClicked(Model.Difficulty difficulty) {
        Log.d("iwantdeath", "view model triangle clicked");
        model.setDifficulty(difficulty);
    }

    // Sets the player avatar based on the avatar buttons
    public void onSpriteClicked(String sprite) {
        model.getPlayer().getAvatar().setSprite(sprite);
    }
}
