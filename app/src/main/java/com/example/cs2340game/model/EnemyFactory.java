package com.example.cs2340game.model;

public abstract class EnemyFactory {

    public Enemy newEnemy(String id, MovementStrategy movementStrategy, int posX, int posY) {
        Enemy enemy = createEnemy(id, movementStrategy, posX, posY);
        return enemy;
    }

    public abstract Enemy createEnemy(String id, MovementStrategy movementStrategy, int posX, int posY);
}
