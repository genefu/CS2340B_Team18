package com.example.cs2340game.model.Powerups;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cs2340game.model.Tile;

public class PowerUpSprite {
    private int id;
    private int posX;
    private int posY;
    private String sprite;
    public PowerUpSprite(int id, int posX, int posY, String sprite) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
    }

    public int checkPowerUp () {
        switch (sprite) {
            case "powerup_speed":
                return 0;
            case "powerup_score":
                return 1;
            case "powerup_range":
                return 2;
            default:
                return 0;
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getID() {
        return id;
    }
    public int getDistance(int x, int y) {
        return (int) Math.sqrt((posX - x) * (posX - x) + (posY - y) * (posY - y)); //gets the distance
    }

    public Bitmap getBitMap(Context context) {
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }
}
