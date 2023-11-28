package com.example.cs2340game.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;
import com.example.cs2340game.R;
import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Powerups.PowerUp;
import com.example.cs2340game.model.Powerups.PowerUpSprite;
import com.example.cs2340game.model.Tile;

//import java.util.HashSet;
import java.util.HashSet;
import java.util.TreeSet;

public class GameRender {
    private MapLayout mapLayout;
    private Context context;
    private Canvas canvas;
    private Bitmap tempBitmap;
    private Model model;
    private ImageView gameView;
    public GameRender(ImageView gameView, int screen, Context context) {

        mapLayout = MapLayout.getInstance(screen);
        tempBitmap = Bitmap.createBitmap(34 * 64,
                20 * 64, Bitmap.Config.RGB_565);
        canvas = new Canvas(tempBitmap);
        this.model = Model.getInstance();
        this.gameView = gameView;
        this.context = context;

        refreshScreen();
    }

    //draws the entire bitmap
    public void drawMap() {
        Tile[][] tileMap = mapLayout.getTileMap();

        Resources res = context.getResources();
        Bitmap waterTile = BitmapFactory.decodeResource(res, R.drawable.watertile);
        Bitmap grassTile = BitmapFactory.decodeResource(res, R.drawable.grasstile);
        Bitmap stoneTile = BitmapFactory.decodeResource(res, R.drawable.stonetile);
        Bitmap exitTile = BitmapFactory.decodeResource(res, R.drawable.exittile);

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 34; col++) {
                Log.d("bruh", row + " " + col);
                Tile tile = tileMap[row][col];
                switch (tile.getType()) {
                case 1: canvas.drawBitmap(waterTile,
                        col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, null);
                    break;
                case 2: canvas.drawBitmap(grassTile,
                        col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, null);
                    break;
                case 3: canvas.drawBitmap(stoneTile,
                        col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, null);
                    break;
                case 4: canvas.drawBitmap(exitTile,
                        col * Tile.TILE_SIZE, row * Tile.TILE_SIZE, null);
                    break;
                default:
                    break;
                }
            }
        }
    }

    public void drawAvatar() {
        Avatar avatar = model.getPlayer().getAvatar();
        Bitmap avatarBitmap = avatar.getBitMap(context);

        int[] gameViewPosition = new int[2];
        gameView.getLocationOnScreen(gameViewPosition);
        int yOffset = gameViewPosition[1];
        canvas.drawBitmap(avatarBitmap, gameViewPosition[0] + avatar.getPosX()
                - Avatar.AVATAR_SIZE / 2, gameViewPosition[1] + avatar.getPosY()
                - Avatar.AVATAR_SIZE / 2 - yOffset, null);
    }

    //draws all enemies on the map
    public void drawEnemies() {
        TreeSet<Enemy> enemySet = model.getUpdatedRenderedEnemies();
        int[] gameViewPosition = new int[2];
        gameView.getLocationOnScreen(gameViewPosition);
        int yOffset = gameViewPosition[1];
        for (Enemy e: enemySet) {
            Bitmap enemyBitmap = e.getBitmap(context);
            canvas.drawBitmap(enemyBitmap, gameViewPosition[0] + e.getPosX()
                    - Avatar.AVATAR_SIZE / 2, gameViewPosition[1] + e.getPosY()
                    - Avatar.AVATAR_SIZE / 2 - yOffset, null);
        }
    }

    //draws all powerups on the map
    public void drawPowerUps() {
        HashSet<PowerUpSprite> powerUpSet = model.getRenderedPowerUps();
        int[] gameViewPosition = new int[2];
        gameView.getLocationOnScreen(gameViewPosition);
        int yOffset = gameViewPosition[1];
        for (PowerUpSprite p: powerUpSet) {
            Bitmap powerUpBitmap = p.getBitMap(context);
            canvas.drawBitmap(powerUpBitmap, gameViewPosition[0] + p.getPosX()
                    - Avatar.AVATAR_SIZE / 2, gameViewPosition[1] + p.getPosY()
                    - Avatar.AVATAR_SIZE / 2 - yOffset, null);
        }
    }

    //redraws map, avatar, and enemies
    public void refreshScreen() {
        drawMap();
        drawAvatar();
        drawEnemies();
        drawPowerUps();
        showTilePositions();

        gameView.setImageBitmap(Bitmap.createScaledBitmap(new BitmapDrawable(context.getResources(),
                tempBitmap).getBitmap(), mapLayout.getViewWidth(),
                mapLayout.getViewHeight(), false));
        gameView.invalidate();
    }


    //shows tile pos of avatar
    public void showTilePositions() {
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 34; col++) {
                canvas.drawRect(col * Tile.TILE_SIZE, row * Tile.TILE_SIZE,
                        col * Tile.TILE_SIZE + 2, row * Tile.TILE_SIZE + 2, new Paint(Color.RED));
            }
        }
    }


    public MapLayout getMapLayout() {
        return mapLayout;
    }
}