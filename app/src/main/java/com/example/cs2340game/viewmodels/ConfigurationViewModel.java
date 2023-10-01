package com.example.cs2340game.viewmodels;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.BR;
import com.example.cs2340game.model.GameTimer;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

import android.widget.Button;
import android.widget.TextView;
import com.example.cs2340game.R;
import android.view.View;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    //Constructor
    public ConfigurationViewModel() {

    }

    // Sets the difficulty based on the difficulty buttons
    public void onDifficultyClicked(int difficulty) {
        Log.d("iwantdeath", "view model triangle clicked");
        Model.setDifficulty(difficulty);
    }

    // Sets the player avatar based on the avatar buttons
    public void onSpriteClicked(String avatar) {
        Model.getPlayer().setAvatar(avatar);
    }
}
