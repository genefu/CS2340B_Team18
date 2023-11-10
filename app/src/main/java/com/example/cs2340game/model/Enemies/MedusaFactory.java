package com.example.cs2340game.model.Enemies;

import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;

public class MedusaFactory extends EnemyFactory {
    private static MedusaFactory factoryInstance;
    private MedusaFactory() {

    }

    public static MedusaFactory getInstance() {
        if (factoryInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (factoryInstance == null) {
                        factoryInstance = new MedusaFactory();
                    }
                }
            }
        }
        return factoryInstance;
    }
    @Override
    public Enemy createEnemy(int id, int posX, int posY)  {
        return new MedusaEnemy(id, posX, posY);
    }
}
