package com.example.cs2340game.model;

public class E1Factory extends EnemyFactory {

    @Override
    public Enemy createEnemy(String id, MovementStrategy movementStrategy, int posX, int posY) {
        return new E1Spider(id, movementStrategy, posX, posY);
    }
}
