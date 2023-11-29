package com.example.cs2340game.model;

//import android.util.Log;

//import androidx.annotation.Nullable;

import android.util.Log;

import com.example.cs2340game.model.Enemies.DevilEnemy;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.Enemies.EnemyFactory;
import com.example.cs2340game.model.Enemies.MedusaEnemy;
import com.example.cs2340game.model.Enemies.MedusaFactory;
import com.example.cs2340game.model.Enemies.SirenEnemy;
import com.example.cs2340game.model.Enemies.SpiderEnemy;
import com.example.cs2340game.model.Powerups.PowerUpSprite;

//import java.util.HashSet;
import java.util.HashSet;
import java.util.TreeSet;

//Main Model
public class Model {
    private static Model modelInstance;
    private Score score;
    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private Difficulty difficulty;
    private Leaderboard leaderboard;
    private Player player;
    private TreeSet<Enemy> renderedEnemies; //Sorted based on damage to player
    private HashSet<PowerUpSprite> renderedPowerUps;
    private int screenWidth;
    private int screenHeight;

    public static final int LEADERBOARD_SIZE = 10;
    //public static final int WIN_THRESHOLD = 1000;
    public static final int REFRESH_RATE = 40;


    //TODO enum variable for different weapon types

    private Model() {
        this.difficulty = Difficulty.MEDIUM;
        //leaderboard in descending order
        this.leaderboard = Leaderboard.getInstance();
        this.player = Player.getInstance(null);
        this.renderedEnemies = new TreeSet<>();
        this.renderedPowerUps = new HashSet<>();
        //increments when objectives met (kill enemy, beat room), and lowers over time
        this.score = new Score(player.getName(), 20, "");
        //testLeaderboard(leaderboard);

        this.screenWidth = 0; //Resources.getSystem().getDisplayMetrics().widthPixels;
        this.screenHeight = 0; //Resources.getSystem().getDisplayMetrics().heightPixels;
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

    public static void clearInstance() {
        modelInstance = null;
    }

    //for testing, implement later to have model save highest score/most recent score
    public void setScore(Score score) {
        this.score = score;
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

    // Determines if the player won or lost
    public boolean isWinner() {
        return player.getHealth() > 0;
    }

    public void addEnemy(Enemy enemy) {
        renderedEnemies.add(enemy);
    }

    public void removeEnemy(int id) {
        TreeSet<Enemy> newEnemies = new TreeSet<>();
        for(Enemy e: renderedEnemies) {
            if (e.getID() != id) {
                newEnemies.add(e);
            }
        }
        renderedEnemies = newEnemies;
    }

    public void addPowerUp(PowerUpSprite powerUp) { renderedPowerUps.add(powerUp); }

    public void removePowerUp(int id) {
        HashSet<PowerUpSprite> copy = new HashSet<>();
        for(PowerUpSprite p: renderedPowerUps) {
            if (p.getID() != id) {
                copy.add(p);
            }
        }
        renderedPowerUps = copy;
        Log.d("powerup", "Removed powerup id " + id + ", hashset is now size " + renderedPowerUps.size());
    }

    public void clearEnemies() {
        //Log.d("EnemiesC", "enemies cleared");
        renderedEnemies.clear();
    }

    public void clearPowerUps() {
        renderedPowerUps.clear();
    }

    public TreeSet<Enemy> getUpdatedRenderedEnemies() {
        return updateEnemyPos();
    }
    public TreeSet<Enemy> updateEnemyPos() {
        for (Enemy e: renderedEnemies) {
            if (e instanceof SpiderEnemy) {
                ((SpiderEnemy) e).movement();
            }
            if (e instanceof MedusaEnemy) {
                ((MedusaEnemy) e).movement();
            }
            if (e instanceof DevilEnemy) {
                ((DevilEnemy) e).movement();
            }
            if (e instanceof SirenEnemy) {
                ((SirenEnemy) e).movement();
            }
        }
        return renderedEnemies;
    }

    public HashSet<PowerUpSprite> getRenderedPowerUps() {
        return renderedPowerUps;
    }
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

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
    //getter for rendered enemies
    public TreeSet<Enemy> getRenderedEnemies() {
        return renderedEnemies;
    }

    // Getter for score
    public Score getScore() {
        return score;
    }
    //setter for screenwidth
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
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