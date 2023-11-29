package com.example.cs2340game.model.Powerups;

import android.util.Log;

public class RangeUpDecorator extends PowerUpDecorator {
    public RangeUpDecorator(PowerUp powerUp) {
        super(powerUp);
    }

    @Override
    public void applyPowerUp() {
        super.applyPowerUp();
        Log.d("powerup", "Range up!");
    }

    @Override
    public void removePowerUp() {
        super.removePowerUp();
        Log.d("powerup", "Range normal");
    }
}
