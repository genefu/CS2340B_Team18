package com.example.cs2340game.model;

import android.content.res.Resources;

import androidx.annotation.Nullable;

import java.util.TreeSet;

//Main Model
public class Model {
    @Nullable
    private static String playerName;
    private static Model modelInstance;
    private Score score;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private Difficulty difficulty;
    private Leaderboard leaderboard;
    private Player player;

    private static final int LEADERBOARD_SIZE = 10;
    private static final int WIN_THRESHOLD = 1000;

    //TODO enum variable for different weapon types

    private Model() {
        this.difficulty = Difficulty.MEDIUM;
        //leaderboard in descending order
        this.leaderboard = Leaderboard.getInstance();
        this.player = Player.getInstance(null);
        //increments when objectives met (kill enemy, beat room), and lowers over time
        this.score = new Score(player.getName(), 20, "");
        //testLeaderboard(leaderboard);
    }

    // Creates (if not already created) and returns the model instance
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

    // Adds dummy scores to leaderboard
    /*public void testLeaderboard(TreeSet<Score> leaderboard) {
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
    }*/

    // Updates leaderboard with a new score
    public void updateLeaderboard(Score score) {
        TreeSet<Score> leaderboardSet = leaderboard.getLeaderboardSet();
        leaderboardSet.add(score);
        if (leaderboardSet.size() > LEADERBOARD_SIZE) {
            leaderboardSet.pollLast(); //Removes smallest score
        }
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    // Determines if the player won or lost
    public boolean isWinner() {
        return score.getScoreValue() > WIN_THRESHOLD; //TODO get real win condition
    }

    //TODO make getters and setters
    public Player getPlayer() {
        return player;
    }

    // Getter for difficulty
    public Difficulty getDifficulty() {
        return difficulty;

    }
    // Getter for playerName
    public String getPlayerName() {
        return player.getName();
    }

    // Getter for score
    public Score getScore() {
        return score;
    }

    // Setter for playerName
    public void setPlayerName(String playerName) {
        player.setName(playerName);
    }

    // Setter for difficulty
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        player.updateDifficultyStats(difficulty);
        //Log.d("iwantdeath", "difficulty set in model " + Model.difficulty);
    }

    // Getter for leaderboard
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public int getLeaderBoardSize() {
        return leaderboard.getLeaderboardSet().size();
    }
}