package com.example.cs2340game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cs2340game.R;

public class Tile {
    private Context context;
    private Bitmap bitmap;
    public Tile(Context context, int tile) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), tile);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
