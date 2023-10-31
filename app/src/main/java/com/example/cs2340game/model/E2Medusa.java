package com.example.cs2340game.model;

public class E2Medusa {

    private static E2Medusa medusaInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private Avatar avatar;

    private E2Medusa(String name) {
        this.name = name;
        baseHealth = 1;
        this.baseDefense = 10;
        this.baseStrength = 1000;
        this.speed = 30;
    }

    private E2Medusa(){
        this.baseHealth = 1;
        this.baseDefense = 10;
        this.baseStrength = 1000;
        this.name = "Medusa";
        this.speed = 30;
    }

    public static E2Medusa getInstance(String name) {
        if (medusaInstance == null) {
            synchronized (Model.class) {
                synchronized (E2Medusa.class) {
                    if (medusaInstance == null) {
                        medusaInstance = new E2Medusa(name);
                    }
                }
            }
        }
        return medusaInstance;
    }

    public static E2Medusa getInstance() {
        if (medusaInstance == null) {
            synchronized (E2Medusa.class) {
                if (medusaInstance == null) {
                    throw new IllegalArgumentException(
                            "Player doesn't exist, needs a name parameter");
                }
            }
        }
        return medusaInstance;
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
