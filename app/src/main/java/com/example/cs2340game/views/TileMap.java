package com.example.cs2340game.views;

import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import com.example.cs2340game.R;

public class TileMap {
    private static final int VIEW_SIZE = 100;
    private Bitmap myBitmap;
    private Canvas canvas;
    private Bitmap tileSet;

    public TileMap(View gameView, String screen) {
        int height = VIEW_SIZE; //gameView.getHeight();
        int width = VIEW_SIZE; //gameView.getWidth();
        Log.d("iwantdeath", width + " " + height);
        Resources res = gameView.getResources();
        //set the tile set based on which game screen it is
        if (screen.equals("1")) {
            this.tileSet = BitmapFactory.decodeResource(res, R.drawable.red_tile);
            this.tileSet = Bitmap.createScaledBitmap(this.tileSet, width, height, false);
        } else if (screen.equals("2")) {
            this.tileSet = BitmapFactory.decodeResource(res, R.drawable.gray_tile);
            this.tileSet = Bitmap.createScaledBitmap(this.tileSet, width, height, false);
        } else {
            this.tileSet = BitmapFactory.decodeResource(res, R.drawable.black_tile);
            this.tileSet = Bitmap.createScaledBitmap(this.tileSet, width, height, false);
        }
        myBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(myBitmap);
        canvas.drawBitmap(tileSet, 0, 0, null);
        gameView.draw(canvas);

    }

    public Bitmap getTileSet() {
        return myBitmap;
    }
}
