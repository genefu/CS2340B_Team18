package com.example.cs2340game.model;

public class E3Factory extends EnemyFactory{

    @Override
    public Enemy createEnemy(String id) {
        return new E3Devil(id);
    }
}
