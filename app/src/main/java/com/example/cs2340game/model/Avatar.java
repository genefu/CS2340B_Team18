package com.example.cs2340game.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.math.MathUtils;

import com.example.cs2340game.R;
import com.example.cs2340game.views.MapLayout;

import java.util.Arrays;
import java.util.HashSet;

public class Avatar {
    private static Avatar avatarInstance;
    public static final int AVATAR_SIZE = 64;
    private Vector movementVector;
    private MovementStrategy movementStrategy;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private boolean isOnExit;
    private Direction directionFacing;
    private int posX; //position of center x
    private int posY; //position of center y
    enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    enum CollisionBox {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, NONE
    }

    private Avatar(String sprite, MovementStrategy movementStrategy) {
        movementVector = new Vector();
        this.movementStrategy = movementStrategy;
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.directionFacing = Direction.UP;
        this.posX = AVATAR_SIZE / 2 + Tile.TILE_SIZE * 2; //TODO replace with room 1 starting position
        this.posY = AVATAR_SIZE / 2 + Tile.TILE_SIZE * 2; //TODO replace with room 1 starting position
        isOnExit = false;
    }

    public static Avatar getInstance(String sprite) {
        if (avatarInstance == null) {
            synchronized (Model.class) {
                synchronized (Player.class) {
                    if (avatarInstance == null) {
                        avatarInstance = new Avatar(sprite, new WalkStrategy());
                    }
                }
            }
        }
        return avatarInstance;
    }

    public static Avatar getInstance() {
        if (avatarInstance == null) {
            synchronized (Player.class) {
                if (avatarInstance == null) {
                    throw new IllegalArgumentException(
                            "Avatar doesn't exist, needs a sprite parameter");
                }
            }
        }
        return avatarInstance;
    }

    public void applyVector(Vector v) {
        //Log.d("keyPress", "Applied Vector");
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
            //Log.d("keyPress", "Added Vector: " + movementVector.getX() + " " + movementVector.getY());
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
            directionFacing = Direction.UP;
        } else if (y > 0 && x > 0) {
            directionFacing = Direction.UP_RIGHT;
        } else if (y == 0 && x > 0) {
            directionFacing = Direction.RIGHT;
        } else if (y < 0 && x > 0) {
            directionFacing = Direction.DOWN_RIGHT;
        } else if (y < 0 && x == 0) {
            directionFacing = Direction.DOWN;
        } else if (y < 0 && x < 0) {
            directionFacing = Direction.DOWN_LEFT;
        } else if (y == 0 && x < 0) {
            directionFacing = Direction.LEFT;
        } else if (y > 0 && x < 0) {
            directionFacing = Direction.UP_LEFT;
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
        CollisionBox collisionBox;
        if ((collisionBox = checkCollision()) != CollisionBox.NONE) {
            moveToValidPosition(collisionBox);
        }
        //Log.d("collision", "After: " + posX + " " + posY);
    }

    public CollisionBox checkCollision() {
        Tile[][] tileMap = MapLayout.getInstance().getTileMap();
        Tile[] coveredTiles = getTileCoverage(tileMap);
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isExit()) {
                isOnExit = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (coveredTiles[i].isWall()) {
                //Log.d("collision", "Collision Detected with tile at: " + coveredTiles[i].getCol() + ", " + coveredTiles[i].getRow());
                switch (i) {
                    case 0: return CollisionBox.TOP_LEFT;
                    case 1: return CollisionBox.TOP_RIGHT;
                    case 2: return CollisionBox.BOTTOM_LEFT;
                    case 3: return CollisionBox.BOTTOM_RIGHT;
                    default: throw new IllegalArgumentException("Invalid collision box");
                }
            }
        }
        return CollisionBox.NONE;
    }

    public void moveToValidPosition(CollisionBox collisionBox) {
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
            default: return;
        }
        //Log.d("collision", "TL: " + (posX - 32) + " " + (posY - 32) + "\nBR: " + (posX + 31) + " " + (posY + 31));
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
        }
    }

    public boolean isOnExit() {
        return isOnExit;
    }

    public void resetOnExit() {
        isOnExit = false;
    }

    public void setPosition(int x, int y) {
        posX = AVATAR_SIZE / 2 + x;
        posY = AVATAR_SIZE / 2 + y;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getSprite() {
        return sprite;
    }
    public Vector getMovementVector() {
        return movementVector;
    }

    public Direction getDirectionFacing() {
        return directionFacing;
    }

    public Bitmap getBitMap(Context context) {
        Resources res = context.getResources();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }

    // Returns the set of all tiles that the avatar is currently on top of
    public Tile[] getTileCoverage(Tile[][] tileMap) {
        Tile[] tilesCovered = new Tile[4];
        int top = MathUtils.clamp((int) ((posY - 32) / Tile.TILE_SIZE), 0, 19);
        int left = MathUtils.clamp((int) ((posX - 32) / Tile.TILE_SIZE), 0, 33);

        int bottom = MathUtils.clamp((int) ((posY + 31) / Tile.TILE_SIZE), 0, 19);
        int right = MathUtils.clamp((int) ((posX + 31) / Tile.TILE_SIZE), 0, 33);
        tilesCovered[0] = (tileMap[top][left]); // Top left corner
        tilesCovered[1] = (tileMap[top][right]); // Top right corner
        tilesCovered[2] = (tileMap[bottom][left]); // Bottom left corner
        tilesCovered[3] = (tileMap[bottom][right]); // Bottom right corner
        return tilesCovered;
    }
}
