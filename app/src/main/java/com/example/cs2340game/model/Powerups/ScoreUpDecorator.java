package com.example.cs2340game.model.Powerups;

public class ScoreUpDecorator extends PowerUpDecorator {
    public ScoreUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setScoreMultiplier(1.5);
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setScoreMultiplier(1);
    }
}
