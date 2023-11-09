package com.example.cs2340game.model;

public abstract class EnemyFactory {

    public Enemy newEnemy() {
        Enemy enemy = createEnemy();
        return enemy;
    }

    public abstract Enemy createEnemy();
}
