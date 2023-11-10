package com.example.cs2340game.model;

public class E4Factory extends EnemyFactory {

    @Override
    public Enemy createEnemy(String id, MovementStrategy movementStrategy, int posX, int posY) {
        return new E4Siren(id, movementStrategy, posX, posY);
    }
}
