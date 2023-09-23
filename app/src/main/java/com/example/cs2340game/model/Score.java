package com.example.cs2340game.model;

//Stores a score and the player that achieved it for the leaderboard
public class Score implements Comparable<Score> {
    private String playerName;
    private int score;
    //Constructor
    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    //Compare for sorted leaderboard list
    //outputs negative if larger due to treesets sorting in ascending order by default
    @Override
    public int compareTo(Score score) {
        return score.score - this.score;
    }
}
