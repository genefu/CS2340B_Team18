package com.example.cs2340game.model.Enemies;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

public class DevilFactory extends EnemyFactory {
    private static DevilFactory factoryInstance;
    private DevilFactory() {

    }

    public static DevilFactory getInstance() { //get instance of enemy devil
        if (factoryInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (factoryInstance == null) {
                        factoryInstance = new DevilFactory();
                    }
                }
            }
        }
        return factoryInstance;
    }
    @Override
    public Enemy createEnemy(int id, int posX, int posY) {
        return new DevilEnemy(id, posX, posY);
    }
}
