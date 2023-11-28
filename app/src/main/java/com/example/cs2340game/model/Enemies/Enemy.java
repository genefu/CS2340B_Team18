package com.example.cs2340game.model.Enemies;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class Enemy {
    public static final int ENEMY_SIZE = 64;
    public abstract void attack(); //TBD
    public abstract int getDistance(int x, int y);
    public abstract int getHealth();
    public abstract void setHealth(int h);
    public abstract int getStrength();
    public abstract int getPosX();
    public abstract int getPosY();
    public abstract int getID();
    public abstract Bitmap getBitmap(Context context);
}
