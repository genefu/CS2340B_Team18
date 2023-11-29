package com.example.cs2340game.model.Powerups;

import android.util.Log;

public class ScoreUpDecorator extends PowerUpDecorator {
    public ScoreUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setScoreMultiplier(1.5);
        Log.d("powerup", "Score up!");
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setScoreMultiplier(1);
        Log.d("powerup", "Score normal");
    }
}
