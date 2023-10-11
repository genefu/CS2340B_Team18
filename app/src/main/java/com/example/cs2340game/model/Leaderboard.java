package com.example.cs2340game.model;

import java.util.TreeSet;

public class Leaderboard {
    private TreeSet<Score> leaderboard;
    private Score scoreEntry;
    private Model model;
    private static Leaderboard leaderboardInstance;

    public Leaderboard() {
        model = model.getInstance();
        leaderboard = model.getLeaderboard();
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

}
