package com.example.cs2340game.model;

public class E2Factory extends EnemyFactory{

    @Override
    public Enemy createEnemy()  {
        return new E2Medusa();
    }
}
