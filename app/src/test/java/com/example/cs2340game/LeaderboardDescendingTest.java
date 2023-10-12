package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Score;

import java.util.TreeSet;

public class LeaderboardDescendingTest {
    @Test
    public void LeaderboardDescending() {
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

        assertEquals(testBoard, model.getLeaderboard());

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
}


