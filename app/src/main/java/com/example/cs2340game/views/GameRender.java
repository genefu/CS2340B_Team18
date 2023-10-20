package com.example.cs2340game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.example.cs2340game.R;
import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Model;

public class GameRender {
    private MapLayout mapLayout;
    private Context context;
    private Canvas canvas;
    private Bitmap tempBitmap;
    private Model model;
    private ImageView gameView;
    public GameRender(ImageView gameView, int screen, Context context) {

        mapLayout = MapLayout.getInstance(screen);
        tempBitmap = Bitmap.createBitmap(34*64,
                20*64, Bitmap.Config.RGB_565);
        canvas = new Canvas(tempBitmap);
        this.model = Model.getInstance();
        this.gameView = gameView;
        this.context = context;

        refreshScreen();
        Log.d("test", gameView.getLayoutParams().width + " " + gameView.getLayoutParams().height + " " + model.getScreenWidth() + " " + model.getScreenHeight());
    }
    public void drawMap(Canvas canvas) {
        //set the tile set based on which game screen it is
        Tile grassTile = new Tile(context, R.drawable.grasstile);
        Tile stoneTile = new Tile(context, R.drawable.stonetile);
        Tile waterTile = new Tile(context, R.drawable.watertile);
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 34; col++) {
                Log.d("bruh", row + " " + col);
                switch (mapLayout.getMapLayout()[row][col]) {
                    //case 1: canvas.drawBitmap(stoneTile.getBitmap(),null, destinationRect, null);
                    case 1: canvas.drawBitmap(waterTile.getBitmap(),
                            col * 64, row * 64, null);
                        break;
                    case 2: canvas.drawBitmap(grassTile.getBitmap(),
                            col * 64, row * 64, null);
                        break;
                    case 3: canvas.drawBitmap(stoneTile.getBitmap(),
                            col * 64, row * 64, null);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void drawAvatar(Canvas canvas) {
        Avatar avatar = model.getPlayer().getAvatar();
        Bitmap avatarBitmap = avatar.getBitMap(context);
        int[] gameViewPosition = new int[2];
        gameView.getLocationOnScreen(gameViewPosition);
        canvas.drawBitmap(avatarBitmap, gameViewPosition[0] + avatar.getPosX(), gameViewPosition[1] + avatar.getPosY(), null);
    }

    public void refreshScreen() {
        drawMap(canvas);
        drawAvatar(canvas);

        gameView.setImageBitmap(Bitmap.createScaledBitmap(new BitmapDrawable(context.getResources(), tempBitmap).getBitmap(), mapLayout.getViewWidth(), mapLayout.getViewHeight(), false));
        gameView.invalidate();
    }

    public MapLayout getMapLayout() {
        return mapLayout;
    }
//
//    public Bitmap getTileSet() {
//        return myBitmap;
//    }

}