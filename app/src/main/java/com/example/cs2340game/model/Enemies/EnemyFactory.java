package com.example.cs2340game.model.Enemies;

import com.example.cs2340game.model.MovementStrategies.MovementStrategy;

public abstract class EnemyFactory {

    public Enemy newEnemy(int id, MovementStrategy movementStrategy, int posX, int posY) {
        Enemy enemy = createEnemy(id, posX, posY);
        return enemy;
    }

    public abstract Enemy createEnemy(int id, int posX, int posY);
}
