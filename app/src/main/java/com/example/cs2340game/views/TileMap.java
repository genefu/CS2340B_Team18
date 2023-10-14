package com.example.cs2340game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import com.example.cs2340game.R;
import com.example.cs2340game.model.Model;

public class TileMap {
    private static final int VIEW_SIZE = 100;
    private Canvas canvas;
    private Bitmap tileSet;
    private Tile[][] tilemap;
    private MapLayout mapLayout;
    private Context context;
    private Model model;
    private Rect destinationRect;
    public TileMap(View gameView, String screen, Context context) {
        Resources res = gameView.getResources();
        Canvas canvas = new Canvas();
        mapLayout = new MapLayout();
        //set the tile set based on which game screen it is
        Tile grassTile = new Tile(context, R.drawable.grasstile);
        Tile stoneTile = new Tile(context, R.drawable.stonetile);
        Tile waterTile = new Tile(context, R.drawable.watertile);
        for (int row = 0; row < 20 ; row++) {
            for (int col = 0; col < 34; col++) {
                switch (mapLayout.getMapLayout()[row][col]) {
                    //case 1: canvas.drawBitmap(stoneTile.getBitmap(),null, destinationRect, null);
                    case 1: canvas.drawBitmap(stoneTile.getBitmap(),col*64, row*64, null);
                    case 2: canvas.drawBitmap(grassTile.getBitmap(),col*64, row*64, null);;
                    case 3: canvas.drawBitmap(waterTile.getBitmap(),col*64, row*64, null);;
                }
            }
        }

        gameView.draw(canvas);

    }
//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(myBitmap, VIEW_SIZE, VIEW_SIZE, null);
//    }
//
//    public Bitmap getTileSet() {
//        return myBitmap;
//    }

}