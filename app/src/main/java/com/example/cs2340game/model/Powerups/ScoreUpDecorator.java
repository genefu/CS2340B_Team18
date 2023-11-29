package com.example.cs2340game.model.Powerups;

import android.util.Log;

public class ScoreUpDecorator extends PowerUpDecorator {
    public ScoreUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setScoreMultiplier(2);
        Log.d("powerup", "Score up! Player score multiplier is now " + getPlayer().getScoreMultiplier());
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setScoreMultiplier(1);
        Log.d("powerup", "Score normal");
    }
}
