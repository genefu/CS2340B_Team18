package com.example.cs2340game.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.cs2340game.BR;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

import android.widget.Button;
import android.widget.TextView;
import com.example.cs2340game.R;
import android.view.View;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    //@Bindable
    private String playerName;

    public String getPlayerName() {
        return this.playerName;
    }

    //Setter for playerName in Model
    public void setPlayerName(String playerName, View view) {
        if (playerName == null || playerName.isBlank()) {
            ((TextView) view.findViewById(R.id.nameRequirement)).setText("Premium");
        } else {
            this.playerName = playerName;
            Model.setPlayerName(playerName);
            view.findViewById(R.id.next_button).setEnabled(true);
        }
        //notifyPropertyChanged(BR.playerName); //TODO FIX, updates the view using binded data
        //TODO connect to text input
    }

    //Constructor
    public ConfigurationViewModel() {
        playerName = null;
    }

    // Sets the difficulty based on character sprite choice
    public void onDifficultyClicked(int difficulty) {
        Log.d("iwantdeath", "view model triangle clicked");
        Model.setDifficulty(difficulty);
    }

    public void onSpriteClicked(String avatar) {
        Model.getPlayer().setAvatar(avatar);
    }
}
