package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340game.model.Model;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DifficultyUnitTests {
    @Test
    public void correctHealthBasedOnDifficulty() {
        Model model = Model.getInstance();
        model.setDifficulty(Model.Difficulty.HARD);
        assertEquals(25, Model.getPlayer().getHealth());
        model.setDifficulty(Model.Difficulty.MEDIUM);
        assertEquals(50, Model.getPlayer().getHealth());
        model.setDifficulty(Model.Difficulty.EASY);
        assertEquals(100, Model.getPlayer().getHealth());
    }

    @Test
    public void correctStrengthBasedOnDifficulty() {
        Model model = Model.getInstance();
        model.setDifficulty(Model.Difficulty.HARD);
        assertEquals(25, Model.getPlayer().getStrength());
        model.setDifficulty(Model.Difficulty.MEDIUM);
        assertEquals(50, Model.getPlayer().getStrength());
        model.setDifficulty(Model.Difficulty.EASY);
        assertEquals(100, Model.getPlayer().getStrength());
    }
}