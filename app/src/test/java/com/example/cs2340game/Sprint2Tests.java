package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import static java.lang.Thread.sleep;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Score;
import com.example.cs2340game.viewmodels.ConfigurationViewModel;
import com.example.cs2340game.viewmodels.GameViewModel;

import java.util.TreeSet;

public class Sprint2Tests {

    @Test
    public void correctStrengthBasedOnDifficulty() {
        Model model = Model.getInstance();
        model.setDifficulty(Model.Difficulty.HARD);
        assertEquals(25, model.getPlayer().getStrength());
        model.setDifficulty(Model.Difficulty.MEDIUM);
        assertEquals(50, model.getPlayer().getStrength());
        model.setDifficulty(Model.Difficulty.EASY);
        assertEquals(100, model.getPlayer().getStrength());
    }

    @Test
    public void correctHealthBasedOnDifficulty() {
        Model model = Model.getInstance();
        model.setDifficulty(Model.Difficulty.HARD);
        assertEquals(25, model.getPlayer().getHealth());
        model.setDifficulty(Model.Difficulty.MEDIUM);
        assertEquals(50, model.getPlayer().getHealth());
        model.setDifficulty(Model.Difficulty.EASY);
        assertEquals(100, model.getPlayer().getHealth());
    }

    @Test
    public void LeaderboardDescending() {
        Model model = Model.getInstance();

        model.updateLeaderboard(new Score("p1", 10, "1:3:40"));
        model.updateLeaderboard(new Score("p2", 11, "1:3:40"));
        model.updateLeaderboard(new Score("p3", 12, "1:3:40"));
        model.updateLeaderboard(new Score("p4", 13, "1:3:40"));
        model.updateLeaderboard(new Score("p5", 14, "1:3:40"));
        model.updateLeaderboard(new Score("p6", 15, "1:3:40"));
        model.updateLeaderboard(new Score("p7", 16, "1:3:40"));
        model.updateLeaderboard(new Score("p8", 17, "1:3:40"));
        model.updateLeaderboard(new Score("p9", 18, "1:3:40"));
        model.updateLeaderboard(new Score("p10", 19, "1:3:40"));

        TreeSet<Score> leaderboard = model.getLeaderboard().getLeaderboardSet();
        assertEquals(19, leaderboard.first().getScoreValue());
    }

    @Test
    public void alwaysTenScores() {
        Model model = Model.getInstance();
        TreeSet<Score> testBoard;
        testBoard = model.getLeaderboard().getLeaderboardSet();
        model.updateLeaderboard(new Score("p1", 10, "01:33"));
        model.updateLeaderboard(new Score("p2", 11, "01:33"));
        model.updateLeaderboard(new Score("p3", 12, "01:33"));
        model.updateLeaderboard(new Score("p4", 13, "01:33"));
        model.updateLeaderboard(new Score("p5", 14, "01:33"));
        model.updateLeaderboard(new Score("p6", 15, "01:33"));
        model.updateLeaderboard(new Score("p7", 16, "01:33"));
        model.updateLeaderboard(new Score("p8", 17, "01:33"));
        model.updateLeaderboard(new Score("p9", 18, "01:33"));
        model.updateLeaderboard(new Score("p10", 19, "01:33"));
        model.updateLeaderboard(new Score("p10", 39, "01:33"));

        assertEquals(10, testBoard.size());
    }

    @Test
    public void correctNameStored() {
        Model model = Model.getInstance();
        model.setPlayerName("Player");
        assertEquals("Player", model.getPlayerName());
    }

    @Test
    public void playerWinsWhenThresholdMet() {
        Model model = Model.getInstance();
        model.getScore().setScoreValue(2000);
        assertEquals(true, model.isWinner() );
    }

    @Test
    public void playerLoseWhenThresholdNodeMet() {
        Model model1 = Model.getInstance();
        model1.getScore().setScoreValue(999);
        assertEquals(false, model1.isWinner());
    }

    @Test
    public void scoreStopAtZero() throws InterruptedException {
        Model model = Model.getInstance();
        GameViewModel gvm = new GameViewModel();
        model.getScore().setScoreValue(1);
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
