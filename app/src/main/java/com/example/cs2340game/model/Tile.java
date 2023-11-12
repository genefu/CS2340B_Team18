package com.example.cs2340game.model;

import android.graphics.Rect;
import com.example.cs2340game.R;
import java.util.HashSet;

public class Tile {
    public static final int TILE_SIZE = 64;
    private int resourceId;
    private int type;
    private Rect boundsRect;
    private int row;
    private int col;
    public Tile(int type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
        boundsRect = new Rect(col * TILE_SIZE, row * TILE_SIZE,
                col * TILE_SIZE + TILE_SIZE - 1, row * TILE_SIZE + TILE_SIZE - 1);
        switch (type) {
        case 1: resourceId = R.drawable.watertile;
            break;
        case 2: resourceId = R.drawable.grasstile;
            break;
        case 3: resourceId = R.drawable.stonetile;
            break;
        default:
            break;
        }
    }

    public int getType() {
        return type;
    }

    public Rect getBoundsRect() {
        return boundsRect;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getResourceId() {
        return resourceId;
    }

    public boolean isWall() {
        return type == 3;
    }

    public boolean isWater() {
        return type == 1;
    }

    public boolean isLand() {
        return type == 2; }

    public boolean isExit() {
        return type == 4;
    }

    public HashSet<Tile> getSurroundingTiles(Tile[][] tileMap) {
        HashSet<Tile> surroundingTiles = new HashSet<>();
        for (int r = -1; r < 3; r++) {
            for (int c = -1; c < 3; c++) {
                int loopCol = col + c; // x values
                int loopRow = row + r; // y values
                if (loopCol >= 0 && loopCol <= 34 && loopRow >= 0 && loopRow <= 20) {
                    surroundingTiles.add(tileMap[loopRow][loopCol]);
                }
            }
        }
        return surroundingTiles;
    }
}
