package com.example.cs2340game.model;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.TreeSet;

//Main Model
public class Model {

    @Nullable
    static private String playerName;
    static private int score;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    static private Difficulty difficulty;
    static private TreeSet<Score> leaderboard;
    static private Player player;

    static private final int LEADERBOARD_SIZE = 10;
    static private final int WIN_THRESHOLD = 1000;

    //TODO enum variable for different weapon types

    //Constructor
    public static void initializeModel() {
        Model.difficulty = Difficulty.MEDIUM;
        //leaderboard in descending order
        Model.leaderboard = new TreeSet<>();
        Model.player = new Player(playerName);
        testLeaderboard(leaderboard);
    }

    //Adds dummy scores to leaderboard
    public static void testLeaderboard(TreeSet<Score> leaderboard) {
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
        return score > 1000; //TODO get real win condition
    }

    //TODO make getters and setters
    public static Player getPlayer() {
        return player;
    }

    //Getter for difficulty
    public static Difficulty getDifficulty() {
        return difficulty;

    }
    //Getter for playerName
    public static String getPlayerName() {
        return player.getName();
    }

    //Setter for playerName
    public static void setPlayerName(String playerName) {
        player.setName(playerName);
    }

    //Setter for difficulty
    public static void setDifficulty(Difficulty difficulty) {
        Model.difficulty = difficulty;
        player.updateDifficultyStats();
        //Log.d("iwantdeath", "difficulty set in model " + Model.difficulty);
    }

    //Getter for leaderboard
    public static TreeSet<Score> getLeaderboard() {
        return leaderboard; }
}
