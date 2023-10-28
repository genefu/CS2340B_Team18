package com.example.cs2340game.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.math.MathUtils;

import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;

public class Enemy {
    private static Avatar avatarInstance;
    public static final int ENEMY_SIZE = 64;
    private Vector movementVector;
    private MovementStrategy movementStrategy;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private Avatar.Direction directionFacing;
    private int posX; //position of center x
    private int posY; //position of center y

    enum CollisionBox {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, NONE
    }

    private Enemy(String sprite, MovementStrategy movementStrategy) {
        movementVector = new Vector();
        this.movementStrategy = movementStrategy;
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.directionFacing = Avatar.Direction.UP;
        this.posX = ENEMY_SIZE / 2 + Tile.TILE_SIZE * 2;
        this.posY = ENEMY_SIZE / 2 + Tile.TILE_SIZE * 2;
    }
    public void applyVector(Vector v) {
        //Log.d("keyPress", "Applied Vector");
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
            updateDirection();
            updatePosition();
        }
    }

    public void removeVector(Vector v) {
        //Log.d("keyPress", "Removed Vector");
        if (appliedVectors.remove(v)) {
            movementVector.subtractVector(v);
            updateDirection();
            updatePosition();
        }
    }

    public void clearVectors() {
        appliedVectors.clear();
        movementVector = new Vector();
    }

    public void updateDirection() {
        double x = movementVector.getX();
        double y = -movementVector.getY();
        if (y > 0 && x == 0) {
            directionFacing = Avatar.Direction.UP;
        } else if (y > 0 && x > 0) {
            directionFacing = Avatar.Direction.UP_RIGHT;
        } else if (y == 0 && x > 0) {
            directionFacing = Avatar.Direction.RIGHT;
        } else if (y < 0 && x > 0) {
            directionFacing = Avatar.Direction.DOWN_RIGHT;
        } else if (y < 0 && x == 0) {
            directionFacing = Avatar.Direction.DOWN;
        } else if (y < 0 && x < 0) {
            directionFacing = Avatar.Direction.DOWN_LEFT;
        } else if (y == 0 && x < 0) {
            directionFacing = Avatar.Direction.LEFT;
        } else if (y > 0 && x < 0) {
            directionFacing = Avatar.Direction.UP_LEFT;
        }
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void updatePosition() {
        int[] temp = new int[]{posX, posY};
        movementStrategy.move(movementVector, temp);
        posX = temp[0];
        posY = temp[1];
        //Log.d("collision", "Before: " + posX + " " + posY);
        Avatar.CollisionBox collisionBox;
        collisionBox = checkCollision();
        if (collisionBox != Avatar.CollisionBox.NONE) {
            moveToValidPosition(collisionBox);
        }
        //Log.d("collision", "After: " + posX + " " + posY);
    }

    public Avatar.CollisionBox checkCollision() {
        Tile[][] tileMap = MapLayout.getInstance().getTileMap();
        Tile[] coveredTiles = getTileCoverage(tileMap);
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isWall()) {
                switch (i) {
                    case 0: return Avatar.CollisionBox.TOP_LEFT;
                    case 1: return Avatar.CollisionBox.TOP_RIGHT;
                    case 2: return Avatar.CollisionBox.BOTTOM_LEFT;
                    case 3: return Avatar.CollisionBox.BOTTOM_RIGHT;
                    default: throw new IllegalArgumentException("Invalid collision box");
                }
            }
        }
        return Avatar.CollisionBox.NONE;
    }

    public void moveToValidPosition(Avatar.CollisionBox collisionBox) {
        int baseX;
        int baseY;
        switch (collisionBox) {
            case TOP_LEFT:
                baseX = posX - 32;
                baseY = posY - 32;
                break;
            case TOP_RIGHT:
                baseX = posX + 31;
                baseY = posY - 32;
                break;
            case BOTTOM_LEFT:
                baseX = posX - 32;
                baseY = posY + 31;
                break;
            case BOTTOM_RIGHT:
                baseX = posX + 31;
                baseY = posY + 31;
                break;
            default:
                return;
        }

        //Log.d("collision", "BaseXY: " + baseX + " " + baseY);
        //Log.d("collision", collisionBox.toString() + " " + directionFacing.toString());
        switch (directionFacing) {
            case UP:
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            case UP_RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            case RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                break;
            case DOWN_RIGHT:
                posX -= baseX % Tile.TILE_SIZE + 1;
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case DOWN:
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case DOWN_LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                posY -= baseY % Tile.TILE_SIZE + 1;
                break;
            case LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                break;
            case UP_LEFT:
                posX += Tile.TILE_SIZE - baseX % Tile.TILE_SIZE;
                posY += Tile.TILE_SIZE - baseY % Tile.TILE_SIZE;
                break;
            default:
                break;
        }
    }

    public void setPosition(int x, int y) {
        posX = ENEMY_SIZE / 2 + x;
        posY = ENEMY_SIZE / 2 + y;
    }

    public void setMovementVector(Vector v) {
        movementVector = v;
    }

    public Bitmap getBitMap(Context context) {
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }

    // Returns the set of all tiles that the avatar is currently on top of
    public Tile[] getTileCoverage(Tile[][] tileMap) {
        Tile[] tilesCovered = new Tile[4];
        int midPoint = ENEMY_SIZE / 2;
        int top = MathUtils.clamp((int) ((posY - midPoint) / Tile.TILE_SIZE), 0, 19);
        int left = MathUtils.clamp((int) ((posX - midPoint) / Tile.TILE_SIZE), 0, 33);

        int bottom = MathUtils.clamp((int) ((posY + midPoint - 1) / Tile.TILE_SIZE), 0, 19);
        int right = MathUtils.clamp((int) ((posX + midPoint - 1) / Tile.TILE_SIZE), 0, 33);
        tilesCovered[0] = (tileMap[top][left]); // Top left corner
        tilesCovered[1] = (tileMap[top][right]); // Top right corner
        tilesCovered[2] = (tileMap[bottom][left]); // Bottom left corner
        tilesCovered[3] = (tileMap[bottom][right]); // Bottom right corner
        return tilesCovered;
    }
}
