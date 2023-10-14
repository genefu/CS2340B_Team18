package com.example.cs2340game.views;

import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import com.example.cs2340game.R;
import com.example.cs2340game.model.Model;

public class TileMap {
    private static final int VIEW_SIZE = 100;
    private Bitmap groundBitmap;
    private Canvas canvas;
    private Bitmap tileSet;
    private Tile[][] tilemap;
    private MapLayout mapLayout;
    private Context context;
    private Model model;
    public TileMap(View gameView, String screen, Context context) {
        Log.d("iwantdeath", width + " " + height);
        Resources res = gameView.getResources();
        //set the tile set based on which game screen it is
        groundBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray_tile);
        for (int row = 0; row < ; row++) {
            for (int col = 0; col < ; col++) {

            }
        }

        gameView.draw(canvas);

    }
    public void draw(Canvas canvas) {
        canvas.drawBitmap(myBitmap, VIEW_SIZE, VIEW_SIZE, null);
    }

    public Bitmap getTileSet() {
        return myBitmap;
    }

}
