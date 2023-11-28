package com.example.cs2340game.views;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Enemies.Enemy;
import com.example.cs2340game.model.Enemies.DevilFactory;
import com.example.cs2340game.model.Enemies.MedusaFactory;
import com.example.cs2340game.model.Enemies.SirenFactory;
import com.example.cs2340game.model.Enemies.SpiderFactory;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Tile;

public class MapLayout {
    private static MapLayout mapLayoutInstance;
    private Model model;
    private SpiderFactory spiderFactory;
    private MedusaFactory medusaFactory;
    private DevilFactory devilFactory;
    private SirenFactory sirenFactory;
    private int viewWidth;
    private int viewHeight;
    private int tileSize;
    private Tile[][] tileMap;
    private MapLayout(int screen) {
        model = Model.getInstance();
        int screenWidth = model.getScreenWidth();
        int screenHeight = model.getScreenHeight();

        if (screenWidth >= 1.7 * screenHeight) {
            viewWidth = screenWidth - screenWidth % 64;
            //MAY NEED TO ROUND BECAUSE OF FLOATING POINTS
            viewHeight = (int) Math.round(viewWidth / 1.7);
            //Log.d("tileSize", "larger width: " + viewWidth + " " + viewHeight);
        }
        if (screenWidth < 1.7 * screenHeight) {
            viewHeight = screenHeight - screenHeight % 64; //gameView.getHeight();
            viewWidth = (int) Math.round(viewHeight * 1.7); //gameView.getWidth();
            //Log.d("tileSize", "larger height: " + viewWidth + " " + viewHeight);
        }

        //Log.d("tileSize", (viewHeight / 20) + " " + (viewWidth / 34) + " " + (screenHeight / 20));
        tileSize = (int) (viewHeight / 20);

        this.spiderFactory = SpiderFactory.getInstance();
        this.medusaFactory = MedusaFactory.getInstance();
        this.devilFactory = DevilFactory.getInstance();
        this.sirenFactory = SirenFactory.getInstance();

        setScreen(screen);
    }

    //Creates (if not already created) and returns the player instance
    public static MapLayout getInstance(int screen) {
        if (mapLayoutInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (mapLayoutInstance == null) {
                        mapLayoutInstance = new MapLayout(screen);
                    }
                }
            }
        }
        return mapLayoutInstance;
    }

    public static MapLayout getInstance() {
        if (mapLayoutInstance == null) {
            synchronized (Player.class) {
                if (mapLayoutInstance == null) {
                    throw new IllegalArgumentException(
                            "MapLayout doesn't exist, needs a screen parameter");
                }
            }
        }
        return mapLayoutInstance;
    }

    public void setScreen(int screen) {
        Avatar avatar = Avatar.getInstance();
        int[][] mapLayout = new int[20][34];
        int offset = Enemy.ENEMY_SIZE / 2;
        switch (screen) {
        case 1:
            mapLayout = createScreen1();
            avatar.setPosition(Tile.TILE_SIZE * 2, Tile.TILE_SIZE * 17);
            model.clearEnemies();
            model.addEnemy(spiderFactory.createEnemy(1, Tile.TILE_SIZE * 8, Tile.TILE_SIZE * 8));
            model.addEnemy(spiderFactory.createEnemy(2, Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 8));
            model.addEnemy(spiderFactory.createEnemy(3, Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 10));
            model.addEnemy(medusaFactory.createEnemy(4, Tile.TILE_SIZE * 16, Tile.TILE_SIZE * 4));
            break;
        case 2:
            mapLayout = createScreen2();
            avatar.setPosition(Tile.TILE_SIZE * 2, Tile.TILE_SIZE * 17);
            model.clearEnemies();
            model.addEnemy(medusaFactory.createEnemy(1, Tile.TILE_SIZE * 12, Tile.TILE_SIZE * 10));
            model.addEnemy(sirenFactory.createEnemy(2, Tile.TILE_SIZE * 16, Tile.TILE_SIZE * 4));
            break;
        case 3:
            mapLayout = createScreen3();
            avatar.setPosition(Tile.TILE_SIZE * 1, Tile.TILE_SIZE * 14);
            model.clearEnemies();
            model.addEnemy(sirenFactory.createEnemy(1, Tile.TILE_SIZE * 8, Tile.TILE_SIZE * 8));
            model.addEnemy(sirenFactory.createEnemy(2, Tile.TILE_SIZE * 10, Tile.TILE_SIZE * 8));
            model.addEnemy(sirenFactory.createEnemy(3, Tile.TILE_SIZE * 9, Tile.TILE_SIZE * 10));
            model.addEnemy(devilFactory.createEnemy(4, Tile.TILE_SIZE * 16, Tile.TILE_SIZE * 1));
            model.addEnemy(devilFactory.createEnemy(5, Tile.TILE_SIZE * 16, Tile.TILE_SIZE * 3));
            break;
        default:
            break;
        }
        tileMap = new Tile[20][34];
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 34; col++) {
                tileMap[row][col] = new Tile(mapLayout[row][col], row, col);
            }
        }
        avatar.resetOnExit();
    }

    //creates the first screen with the correct tiles
    private int[][] createScreen1() {
        int[][] mapLayout = new int[20][34];
        mapLayout = new int[][]
            {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3,
                    3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 3},
            {3, 1, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 3, 3, 1, 1, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 3, 3, 1, 1, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 3, 3,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 1, 1, 3,
                    3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 3},
            {3, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 1, 1, 1, 1,
                    3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 1, 3},
            {3, 2, 2, 2, 2, 2, 2, 3, 3, 1, 1, 1, 1, 1, 1, 1,
                    1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                    3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
        return mapLayout;
    }

    private int[][] createScreen2() {
        int[][] mapLayout = new int[20][34];
        mapLayout = new int[][]
            {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                    3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3},
            {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
                    1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 4},
            {3, 1, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1,
                    1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                    3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
        return mapLayout;
    }

    private int[][] createScreen3() {
        int[][] mapLayout = new int[20][34];
        mapLayout = new int[][]
            {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                    3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2,
                    2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 3},
            {3, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 3},
            {3, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2,
                    2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 3},
            {3, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2,
                    2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
            {3, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
                    2, 2, 2, 2, 2, 4, 3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
            {3, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1,
                    1, 1, 1, 1, 4, 4, 3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
            {3, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                    1, 1, 1, 1, 3, 3, 3, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 3},
            {3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 3},
            {3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                    1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 3},
            {3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1,
                    1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3},
            {3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
                    2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2,
                    2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                    1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                    3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
        return mapLayout;
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }
    public int getViewWidth() {
        return viewWidth;
    }
    public int getViewHeight() {
        return viewHeight;
    }
    public int getTileSize() {
        return tileSize;
    }
}