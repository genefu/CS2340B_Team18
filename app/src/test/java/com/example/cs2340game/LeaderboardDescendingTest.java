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
        assertEquals(19, leaderboard.first().getScore());
    }

    @Test
    public void alwaysTenScores() {
        Model model = Model.getInstance();
        TreeSet<Score> testBoard;
        testBoard = model.getLeaderboard().getLeaderboardSet();
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
        model.updateLeaderboard(new Score("p10", 39, "1:3:40"));

        assertEquals(10, testBoard.size());
    }
}


