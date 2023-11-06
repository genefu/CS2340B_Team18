package com.example.cs2340game.model;

public class E3Factory extends EnemyFactory{

    @Override
    public Enemy createEnemy() {
        return new E3Devil();
    }
}
