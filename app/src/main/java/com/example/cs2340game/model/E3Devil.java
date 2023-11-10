package com.example.cs2340game.model;

public class E3Devil extends Enemy {
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String id;
    private int speed;

    public void attack() {
        //TO BE IMPLEMENTED
    }

    public E3Devil(String id) {
        this.id = id;
        baseHealth = 200;
        this.baseDefense = 10;
        this.baseStrength = 15;
        this.speed = 12;
    }

    public E3Devil(){
        this("Devil");
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
    public void setID(String name) {
        this.id = id;
    }

}
