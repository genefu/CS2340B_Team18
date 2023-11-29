package com.example.cs2340game.model.Powerups;

import android.util.Log;

public class SpeedUpDecorator extends PowerUpDecorator {
    public SpeedUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setSpeed(2);
        Log.d("powerup", "Speed up!");
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setSpeed(1);
        Log.d("powerup", "Speed is now " + super.getPlayer().getAvatar().getSpeed());
    }
}
