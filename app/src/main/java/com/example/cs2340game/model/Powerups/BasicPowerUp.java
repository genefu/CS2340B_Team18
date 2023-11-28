package com.example.cs2340game.model.Powerups;

import com.example.cs2340game.model.Player;

public class BasicPowerUp extends PowerUp {
    protected Player player;
    protected int timeRemaining;

    public BasicPowerUp(Player player) {
        this.player = player;
        timeRemaining = 60;
    }
    @Override
    public void applyPowerUp() {

    }

    @Override
    public void removePowerUp() {

    }

    // Returns true if the powerup has time left.
    @Override
    public boolean decrementTime() {
        timeRemaining--;
        if (timeRemaining == 0) {
            removePowerUp();
            return false;
        }
        return true;
    }

    public Player getPlayer() {
        return player;
    }
}
