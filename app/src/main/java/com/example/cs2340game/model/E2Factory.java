package com.example.cs2340game.model;

public class E2Factory extends EnemyFactory {

    @Override
    public Enemy createEnemy(String id, MovementStrategy movementStrategy, int posX, int posY)  {
        return new E2Medusa(id, movementStrategy, posX, posY);
    }
}
