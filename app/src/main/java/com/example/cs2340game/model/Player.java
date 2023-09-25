package com.example.cs2340game.model;

public class Player {
    private int health;
    private int defense;
    private int strength;
    private String name;
    private int speed;

    public Player() {
        switch (Model.getDifficulty()) {
            case 0: health = 100;
                    strength = 100;
                    break;
            case 1: health = 50;
                    strength = 50;
                    break;
            case 2: health = 25;
                    strength = 25;
                    break;
            default: health = 50;
                     strength = 50;
                     break;
        }
        speed = 100;


    }
    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

}
