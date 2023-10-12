package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import static java.lang.Thread.sleep;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;
import com.example.cs2340game.viewmodels.GameViewModel;
import com.example.cs2340game.views.ConfigurationView;
import com.example.cs2340game.views.GameView;
public class AvatarUnitTests {
    @Test
    public void correctAvatarStored() {
        Player player = Player.getInstance("name");
        ConfigurationViewModel configurationViewModel = new ConfigurationViewModel();
        configurationViewModel.onSpriteClicked("sprite1");
        assertEquals("sprite1", player.getAvatar());
        configurationViewModel.onSpriteClicked("sprite2");
        assertEquals("sprite2", player.getAvatar());
        configurationViewModel.onSpriteClicked("sprite3");
        assertEquals("sprite3", player.getAvatar());
    }

}
