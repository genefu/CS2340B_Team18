package com.example.cs2340game.model;

public abstract class EnemyFactory {

    public Enemy newEnemy(String id) {
        Enemy enemy = createEnemy(id);
        return enemy;
    }

    public abstract Enemy createEnemy(String id);
}
