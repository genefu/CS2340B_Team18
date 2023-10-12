package com.example.cs2340game.model;

import java.util.TreeSet;

public class Leaderboard {
    private static Leaderboard leaderboardInstance;
    private TreeSet<Score> leaderboard;

    private Leaderboard() {
        leaderboard = new TreeSet<>();
    }

    public static Leaderboard getInstance() {
        if (leaderboardInstance == null) {
            synchronized (Leaderboard.class) {
                if (leaderboardInstance == null) {
                    leaderboardInstance = new Leaderboard();
                }
            }
        }
        return leaderboardInstance;
    }

    //Getter for leaderboard
    public TreeSet<Score> getLeaderboardSet() {
        return leaderboard;
    }

}
