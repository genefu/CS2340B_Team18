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
        testBoard = model.getLeaderboard();
        model.updateLeaderboard(new Score("p1", 10));
        model.updateLeaderboard(new Score("p2", 11));
        model.updateLeaderboard(new Score("p3", 12));
        model.updateLeaderboard(new Score("p4", 13));
        model.updateLeaderboard(new Score("p5", 14));
        model.updateLeaderboard(new Score("p6", 15));
        model.updateLeaderboard(new Score("p7", 16));
        model.updateLeaderboard(new Score("p8", 17));
        model.updateLeaderboard(new Score("p9", 18));
        model.updateLeaderboard(new Score("p10", 19));

        assertEquals(testBoard, model.getLeaderboard());

    }

    @Test
    public void alwaysTenScores() {
        Model model = Model.getInstance();
        TreeSet<Score> testBoard;
        testBoard = model.getLeaderboard();
        model.updateLeaderboard(new Score("p1", 10));
        model.updateLeaderboard(new Score("p2", 11));
        model.updateLeaderboard(new Score("p3", 12));
        model.updateLeaderboard(new Score("p4", 13));
        model.updateLeaderboard(new Score("p5", 14));
        model.updateLeaderboard(new Score("p6", 15));
        model.updateLeaderboard(new Score("p7", 16));
        model.updateLeaderboard(new Score("p8", 17));
        model.updateLeaderboard(new Score("p9", 18));
        model.updateLeaderboard(new Score("p10", 19));
        model.updateLeaderboard(new Score("p10", 39));

        assertEquals(10, testBoard.size());
    }
}


