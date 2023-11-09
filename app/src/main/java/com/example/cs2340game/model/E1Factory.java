package com.example.cs2340game.model;

public class E1Factory extends EnemyFactory{

    @Override
    public Enemy createEnemy() {
        return new E1Spider();
    }
}
