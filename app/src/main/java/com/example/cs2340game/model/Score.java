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

    //Setter for playerName
    public String getPlayerName() { return playerName; }

    //Getter for playerName
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    //Setter for score
    public int getScore() { return score; }

    //Getter for score
    public void setScore(int score) { this.score = score; }

    //Compare for sorted leaderboard list
    //outputs negative if larger due to treesets sorting in ascending order by default
    @Override
    public int compareTo(Score score) {
        return score.score - this.score;
    }

    // To string
    @Override
    public String toString() { return "(" + playerName + ", " + score + ")"; }
}
