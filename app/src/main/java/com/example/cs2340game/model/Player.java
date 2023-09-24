package com.example.cs2340game.model;

public class Player {
    private int health;
    private int defense;
    private int strength;
    private String name;
    private int speed;
    private String sprite;

    public Player(int defense, int strength, String name, int speed) {
        if (Model.getDifficulty() == 0 ) {
            this.health = 10;
        } else if (Model.getDifficulty() == 1) {
            this.health = 5;
        } else if (Model.getDifficulty() == 2) {
            this.health = 1;
        }

        this.defense = defense;
        this.strength = strength;
        this.name = Model.getPlayerName();
        this.speed = speed;





}
