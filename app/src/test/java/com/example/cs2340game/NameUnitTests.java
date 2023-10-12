package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import static java.lang.Thread.sleep;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.viewmodels.GameViewModel;
import com.example.cs2340game.views.ConfigurationView;
import com.example.cs2340game.views.GameView;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NameUnitTests {
    @Test
    public void correctNameStored() {
        Model model = Model.getInstance();
        model.setPlayerName("Player");
        assertEquals("Player", model.getPlayerName());
    }
}
