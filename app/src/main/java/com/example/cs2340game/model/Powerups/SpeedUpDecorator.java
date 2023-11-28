package com.example.cs2340game.model.Powerups;

public class SpeedUpDecorator extends PowerUpDecorator {
    public SpeedUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setSpeed(1.2);
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setSpeed(1);
    }
}
