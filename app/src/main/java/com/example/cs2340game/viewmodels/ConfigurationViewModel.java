package com.example.cs2340game.viewmodels;

import android.util.Log;

import androidx.databinding.BaseObservable;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Powerups.BasicPowerUp;
import com.example.cs2340game.views.MapLayout;

//View Model for Configuration Screen
public class ConfigurationViewModel extends BaseObservable {
    private Model model;
    private Avatar avatar;
    private Player player;
    private MapLayout map;
    //Constructor
    public ConfigurationViewModel() {
        this.model = Model.getInstance();
    }

    public void resetGame() {
        player = Player.getInstance();
        avatar = Avatar.getInstance();
        map = MapLayout.getInstance(1);
        map.setScreen(1);
        player.restartPlayer();
        avatar.resetAvatar();
        player.getPowerUp().removePowerUp();
        player.setPowerUp(new BasicPowerUp(Player.getInstance()));
        avatar.setSprite(avatar.getSprite().substring(0, 7));
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
