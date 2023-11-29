package com.example.cs2340game.model.Powerups;

public abstract class PowerUp {
    public abstract void applyPowerUp();

    public abstract void removePowerUp();

    public abstract boolean decrementTime();
}
