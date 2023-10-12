package com.example.cs2340game.model;

import androidx.annotation.Nullable;

import java.util.TreeSet;

//Main Model
public class Model {
    @Nullable
    private static String playerName;
    private static Model modelInstance;
    private int score;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private Difficulty difficulty;
    private TreeSet<Score> leaderboard;
    private Player player;

    private final int LEADERBOARD_SIZE = 10;
    private final int WIN_THRESHOLD = 1000;

    //TODO enum variable for different weapon types

    private Model() {
        this.difficulty = Difficulty.MEDIUM;
        //leaderboard in descending order
        this.leaderboard = new TreeSet<>();
        this.player = Player.getInstance(null);
        this.score = 20; //increments when objectives met (kill enemy, beat room), and lowers over time
        testLeaderboard(leaderboard);
    }

    // Creates (if not already created) and returns the model instance
    public static Model getInstance() {
        return modelInstance == null? modelInstance = new Model(): modelInstance;
    }

    // Adds dummy scores to leaderboard
    public void testLeaderboard(TreeSet<Score> leaderboard) {
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

    // Updates leaderboard with a new score
    public void updateLeaderboard(Score score) {
        leaderboard.add(score);
        if (leaderboard.size() > LEADERBOARD_SIZE) {
            leaderboard.pollLast(); //Removes smallest score
        }
    }

    // Determines if the player won or lost
    public boolean isWinner() {
        return score > WIN_THRESHOLD; //TODO get real win condition
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
    public int getScore() {
        return score;
    }

    // Setter for playerName
    public void setPlayerName(String playerName) {
        player.setName(playerName);
    }

    // Setter for score
    public void setScore(int score) { this.score = score; }

    // Setter for difficulty
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        player.updateDifficultyStats(difficulty);
        //Log.d("iwantdeath", "difficulty set in model " + Model.difficulty);
    }

    // Getter for leaderboard
    public TreeSet<Score> getLeaderboard() {
        return leaderboard; }

    public int getLeaderBoardSize() {
        return leaderboard.size();
    }
}
