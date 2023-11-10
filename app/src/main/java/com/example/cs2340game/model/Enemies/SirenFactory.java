package com.example.cs2340game.model.Enemies;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

public class SirenFactory extends EnemyFactory {
    private static SirenFactory factoryInstance;
    private SirenFactory() {

    }

    public static SirenFactory getInstance() {
        if (factoryInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (factoryInstance == null) {
                        factoryInstance = new SirenFactory();
                    }
                }
            }
        }
        return factoryInstance;
    }
    @Override
    public Enemy createEnemy(int id, int posX, int posY) {
        return new SirenEnemy(id, posX, posY);
    }
}
