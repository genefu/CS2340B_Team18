package com.example.cs2340game.model;

public class E1Spider {

    private static E1Spider spiderInstance;
    private int baseHealth;
    private int baseDefense;
    private int baseStrength;
    private String name;
    private int speed;
    private Avatar avatar;




//    private E1Spider(String name) {
//        speed = 100;
//        this.name = name;
//        baseHealth = 40;
//        this.baseDefense = 10;
//        this.baseStrength = 20;
//        this.speed = 30;
//    }
//
//    private E1Spider(){
//        this.baseHealth = 40;
//        this.baseDefense = 10;
//        this.baseStrength = 20;
//        this.name = "Spider";
//        this.speed = 30;
//    }
//
//    public static E1Spider getInstance(String name) {
//        if (spiderInstance == null) {
//            synchronized (Model.class) {
//                synchronized (E1Spider.class) {
//                    if (spiderInstance == null) {
//                        spiderInstance = new E1Spider(name);
//                    }
//                }
//            }
//        }
//        return spiderInstance;
//    }
//
//    public static E1Spider getInstance() {
//        if (spiderInstance == null) {
//            synchronized (E1Spider.class) {
//                if (spiderInstance == null) {
//                    throw new IllegalArgumentException(
//                            "Player doesn't exist, needs a name parameter");
//                }
//            }
//        }
//        return spiderInstance;
//    }
//
//    // Getter for total health
//    public int getHealth() {
//        return baseHealth;
//    }
//
//    // Getter for total strength
//    public int getStrength() {
//        return baseStrength;
//    }
//
//    // Getter for player name
//    public String getName() {
//        return name;
//    }
//
//    // Getter for player avatar
//    public Avatar getAvatar() {
//        return avatar;
//    }
//
//    // Setter for player avatar
//    public void setName(String name) {
//        this.name = name;
//    }

}

