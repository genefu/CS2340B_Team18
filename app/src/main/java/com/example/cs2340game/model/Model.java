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

    //TODO enum variable for different weapon types

    //Constructor
    public static void initializeModel() {
        Model.playerName = "TESTNAME";
        Model.difficulty = 0;
        Model.score = 0;
        //leaderboard in descending order
        Model.leaderboard = new TreeSet<Score>();
    }

    //Updates leaderboard with a new score
    public static void updateLeaderboard(Score score) {
        leaderboard.add(score);
        if (leaderboard.size() > LEADERBOARD_SIZE) {
            leaderboard.pollLast(); //Removes smallest score
        }
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
}
