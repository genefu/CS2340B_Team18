package com.example.cs2340game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class TileMap {
    private Bitmap myBitmap;
    private Tile[][] tilemap;

    public TileMap() {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        myBitmap = Bitmap.createBitmap(100,100,config);
        Canvas canvas = new Canvas(myBitmap);
        for (int row = 0;row < )
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
