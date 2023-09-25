package com.example.cs2340game.model;

public class Player {
    private int health;
    private int defense;
    private int strength;
    private static String name;
    private int speed;

    private static String avatar;


    public Player() {
        name = Model.getPlayerName();
        avatar = "triangle_sprite";
    }

    public static void setAvatar(String avatar) {
        Player.avatar = avatar;
    }

}
