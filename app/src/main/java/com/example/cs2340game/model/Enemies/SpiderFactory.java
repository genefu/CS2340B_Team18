package com.example.cs2340game.model.Enemies;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

public class SpiderFactory extends EnemyFactory {
    private static SpiderFactory factoryInstance;
    private SpiderFactory() {

    }

    public static SpiderFactory getInstance() {
        if (factoryInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (factoryInstance == null) {
                        factoryInstance = new SpiderFactory();
                    }
                }
            }
        }
        return factoryInstance;
    }
    @Override
    public Enemy createEnemy(int id, int posX, int posY) {
        return new SpiderEnemy(id, posX, posY);
    }
}
