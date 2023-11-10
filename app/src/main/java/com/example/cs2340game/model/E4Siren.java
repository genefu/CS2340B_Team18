package com.example.cs2340game.model;

public class E4Siren extends Enemy {
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;

    public void attack() {
        //TO BE IMPLEMENTED
    }

    public E4Siren(String name) {
        this.id = id;
        baseHealth = 40;
        this.baseDefense = 0;
        this.baseStrength = 10;
        this.speed = 40;
    }

    public E4Siren(){
        this("Siren");
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
