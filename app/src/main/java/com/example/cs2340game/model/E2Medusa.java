package com.example.cs2340game.model;

public class E2Medusa extends Enemy {
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;

    public E2Medusa(String id) {
        this.id = id;
        baseHealth = 1;
        this.baseDefense = 10;
        this.baseStrength = 1000;
        this.speed = 30;
    }

    public E2Medusa() {
        this("Medusa");
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
    public String getID() {
        return id;
    }

    // Setter for player avatar
    public void setID(String id) {
        this.id = id;
    }

}
