package com.example.cs2340game.model;

import android.content.res.Resources;

import androidx.annotation.Nullable;

import java.util.TreeSet;

//Main Model
public class Model {
    @Nullable
    private static String playerName;
    private static Model modelInstance;
    private static int score;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static Difficulty difficulty;
    private static TreeSet<Score> leaderboard;
    private static Player player;

    private static final int LEADERBOARD_SIZE = 10;
    private static final int WIN_THRESHOLD = 1000;

    //TODO enum variable for different weapon types

    private Model() {
        Model.difficulty = Difficulty.MEDIUM;
        //leaderboard in descending order
        Model.leaderboard = new TreeSet<>();
        Model.player = Player.getInstance(null);
        testLeaderboard(leaderboard);
    }

    //Creates (if not already created) and returns the model instance
    public static Model getInstance() {
        if (modelInstance == null) {
            synchronized (Model.class) {
                if (modelInstance == null) {
                    modelInstance = new Model();
                }
            }
        }
        return modelInstance;
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
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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
