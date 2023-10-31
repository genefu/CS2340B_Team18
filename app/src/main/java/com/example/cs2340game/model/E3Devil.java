package com.example.cs2340game.model;

public class E3Devil {
    private static E3Devil devilInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private Avatar avatar;

    private E3Devil(String name) {
        this.name = name;
        baseHealth = 200;
        this.baseDefense = 10;
        this.baseStrength = 15;
        this.speed = 12;
    }

    private E3Devil(){
        this.baseHealth = 200;
        this.baseDefense = 10;
        this.baseStrength = 15;
        this.name = "Devil";
        this.speed = 12;
    }

    public static E3Devil getInstance(String name) {
        if (devilInstance == null) {
            synchronized (Model.class) {
                synchronized (E3Devil.class) {
                    if (devilInstance == null) {
                        devilInstance = new E3Devil(name);
                    }
                }
            }
        }
        return devilInstance;
    }

    public static E3Devil getInstance() {
        if (devilInstance == null) {
            synchronized (E3Devil.class) {
                if (devilInstance == null) {
                    throw new IllegalArgumentException(
                            "Player doesn't exist, needs a name parameter");
                }
            }
        }
        return devilInstance;
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
