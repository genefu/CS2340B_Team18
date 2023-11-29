package com.example.cs2340game.model.Powerups;

import android.util.Log;

public class RangeUpDecorator extends PowerUpDecorator {
    public RangeUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        super.getPlayer().setRange(5);
        Log.d("powerup", "Range up!");
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        super.getPlayer().setRange(3);
        Log.d("powerup", "Range normal");
    }
}
