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
public class ScoreTrackerUnitTests {
    @Test
    public void scoreStopAtZero() throws InterruptedException {
        Model model = Model.getInstance();
        GameViewModel gvm = new GameViewModel();
        model.setScore(1);
        assertEquals(1, model.getScore());
        gvm.decrementScore();
        assertEquals(0, model.getScore());
        gvm.decrementScore();
        assertEquals(0, model.getScore());
    }

    @Test
    public void checkTimeString() {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setTime(0, 0, 0);
        assertEquals("0:00", gameViewModel.getTime());
        gameViewModel.setTime(5, 0, 0);
        assertEquals("0:05", gameViewModel.getTime());
        gameViewModel.setTime(15, 0, 0);
        assertEquals("0:15", gameViewModel.getTime());
        gameViewModel.setTime(15, 4, 0);
        assertEquals("4:15", gameViewModel.getTime());
        gameViewModel.setTime(15, 44, 0);
        assertEquals("44:15", gameViewModel.getTime());
        gameViewModel.setTime(15, 44, 1);
        assertEquals("1:44:15", gameViewModel.getTime());
        gameViewModel.setTime(15, 44, 20);
        assertEquals("20:44:15", gameViewModel.getTime());
        gameViewModel.setTime(15, 44, 2000);
        assertEquals("2000:44:15", gameViewModel.getTime());
        gameViewModel.setTime(60, 44, 20);
        assertEquals("20:45:00", gameViewModel.getTime());
        gameViewModel.setTime(66, 44, 20);
        assertEquals("20:45:06", gameViewModel.getTime());
        gameViewModel.setTime(135, 44, 20);
        assertEquals("20:46:15", gameViewModel.getTime());
        gameViewModel.setTime(135, 60, 20);
        assertEquals("21:02:15", gameViewModel.getTime());
        gameViewModel.setTime(120, 120, 0);
        assertEquals("2:02:00", gameViewModel.getTime());
        gameViewModel.setTime(6023, 0, 0);
        assertEquals("1:40:23", gameViewModel.getTime());
    }
}