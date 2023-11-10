package com.example.cs2340game.model;

public class E1Spider extends Enemy {
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;
    public E1Spider(String id) {
        speed = 100;
        this.id = id;
        baseHealth = 40;
        this.baseDefense = 10;
        this.baseStrength = 20;
        this.speed = 30;
    }

    public void attack() {
        //TO BE IMPLEMENTED
    }

    // Getter for total health
    public int getHealth() {
        return baseHealth;
    }

    // Getter for total strength
    public int getStrength() {
        return baseStrength;
    }

    // Getter for player name
    public String getName() {
        return id;
    }

    // Setter for player avatar
    public void setID(String id) {
        this.id = id;
    }

}

