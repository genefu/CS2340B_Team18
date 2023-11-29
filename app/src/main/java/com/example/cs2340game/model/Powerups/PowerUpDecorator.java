package com.example.cs2340game.model.Powerups;

import com.example.cs2340game.model.Player;

public class PowerUpDecorator extends PowerUp {
    protected PowerUp powerUp;

    public PowerUpDecorator(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void applyPowerUp() {
        this.powerUp.applyPowerUp();
    }

    @Override
    public void removePowerUp() {
        this.powerUp.removePowerUp();
    }

    @Override
    public boolean decrementTime() {
        return this.powerUp.decrementTime();
    }

    public Player getPlayer() {
//        PowerUp test = powerUp;
//        while (!(test instanceof BasicPowerUp)) {
//            test = powerUp.getPowerUp();
//        }
        return ((BasicPowerUp) this.powerUp).getPlayer();
    }
}
