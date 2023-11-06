package com.example.cs2340game.model;

public class E4Siren implements Enemy{
    private static E4Siren sirenInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private Avatar avatar;

    public void attack() {
        //TO BE IMPLEMENTED
    }

    public E4Siren(String name) {
        this.name = name;
        baseHealth = 40;
        this.baseDefense = 0;
        this.baseStrength = 10;
        this.speed = 40;
    }

    public E4Siren(){
        this.baseHealth = 40;
        this.baseDefense = 0;
        this.baseStrength = 10;
        this.name = "Medusa";
        this.speed = 40;
    }

    public static E4Siren getInstance(String name) {
        if (sirenInstance == null) {
            synchronized (Model.class) {
                synchronized (E4Siren.class) {
                    if (sirenInstance == null) {
                        sirenInstance = new E4Siren(name);
                    }
                }
            }
        }
        return sirenInstance;
    }

    public static E4Siren getInstance() {
        if (sirenInstance == null) {
            synchronized (E4Siren.class) {
                if (sirenInstance == null) {
                    throw new IllegalArgumentException(
                            "Player doesn't exist, needs a name parameter");
                }
            }
        }
        return sirenInstance;
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
        return name;
    }

    // Getter for player avatar
    public Avatar getAvatar() {
        return avatar;
    }

    // Setter for player avatar
    public void setName(String name) {
        this.name = name;
    }
}
