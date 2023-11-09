package com.example.cs2340game.model;

public class E4Factory extends EnemyFactory{

    @Override
    public Enemy createEnemy() {
        return new E4Siren();
    }
}
