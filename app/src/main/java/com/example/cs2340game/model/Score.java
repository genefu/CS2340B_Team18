package com.example.cs2340game.model;

//Stores a score and the player that achieved it for the leaderboard
public class Score implements Comparable<Score> {
    private String playerName;
    private int score;
    private String dateTime;

    //Constructor
    public Score(String playerName, int score, String dateTime) {
        this.playerName = playerName;
        this.score = score;
        this.dateTime = dateTime;
    }


    //Setter for playerName
    public String getPlayerName() {
        return playerName; }

    //Getter for playerName
    public void setPlayerName(String playerName) {
        this.playerName = playerName; }

    //Setter for score
    public int getScoreValue() {
        return score;
    }

    //Getter for score
    public void setScoreValue(int score) {
        this.score = score;
    }

    public void addScoreValue(int score, double scoreMultiplier) {
        this.score += (int) (4 * score * scoreMultiplier);
    }

    //getter for date/time
    public String getDateTime() {
        return dateTime;
    }

    //setter for date/time
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    //Compare for sorted leaderboard list
    //outputs negative if larger due to treesets sorting in ascending order by default
    @Override
    public int compareTo(Score score) {
        return score.score - this.score; }

    // To string
    @Override
    public String toString() {
        return "Name: " + playerName + "\nScore: " + score + "\nTime: " + dateTime; }
}

