package com.example.cs2340game.model;

import androidx.annotation.Nullable;

import java.util.Set;
import java.util.TreeSet;

//Main Model
public class Model {

    @Nullable
    static private String playerName;
    static private int score;
    static private int difficulty; //TODO make this an ENUM (0 = easy, 1 = medium)
    static private TreeSet<Score> leaderboard;
    static private final int LEADERBOARD_SIZE = 10;
    static private final int WIN_THRESHOLD = 1000;

    //TODO enum variable for different weapon types

    //Constructor
    public static void initializeModel() {
        Model.playerName = "TESTNAME";
        Model.difficulty = WIN_THRESHOLD;
        Model.score = 0;
        //leaderboard in descending order
        Model.leaderboard = new TreeSet<>();
        testLeaderboard(leaderboard);
    }

    //Adds dummy scores to leaderboard
    static public void testLeaderboard(TreeSet<Score> leaderboard) {
        updateLeaderboard(new Score("a", 1));
        updateLeaderboard(new Score("b", 2));
        updateLeaderboard(new Score("c", 3));
        updateLeaderboard(new Score("d", 4));
        updateLeaderboard(new Score("e", 5));
        updateLeaderboard(new Score("REMOVE", 0));
        updateLeaderboard(new Score("g", 6));
        updateLeaderboard(new Score("h", 7));
        updateLeaderboard(new Score("i", 8));
        updateLeaderboard(new Score("j", 9));
        updateLeaderboard(new Score("k", 10));
    }

    //Updates leaderboard with a new score
    public static void updateLeaderboard(Score score) {
        leaderboard.add(score);
        if (leaderboard.size() > LEADERBOARD_SIZE) {
            leaderboard.pollLast(); //Removes smallest score
        }
    }

    //Determines if the player won or lost
    public static boolean isWinner() {
        return score>1000; //TODO get real win condition
    }

    //TODO make getters and setters
    //Getter for playerName
    public static String getPlayerName() {
        return playerName;
    }

    //Setter for playerName
    public static void setPlayerName(String playerName) {
        Model.playerName = playerName;
    }

    //Getter for leaderboard
    public static TreeSet<Score> getLeaderboard() { return leaderboard; }
}
