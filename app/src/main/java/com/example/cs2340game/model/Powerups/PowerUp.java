package com.example.cs2340game.model.Powerups;

import com.example.cs2340game.model.Player;

public abstract class PowerUp {
    public abstract void applyPowerUp();

    public abstract void removePowerUp();

    public abstract boolean decrementTime();
}
