package com.example.cs2340game.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.cs2340game.R;
import com.example.cs2340game.views.MapLayout;

import java.util.HashSet;

public class Avatar {
    private static Avatar avatarInstance;
    private Vector movementVector;
    private MovementStrategy movementStrategy;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private Direction directionFacing;
    private int posX;
    private int posY;
    enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    private Avatar(String sprite, MovementStrategy movementStrategy) {
        movementVector = new Vector();
        this.movementStrategy = movementStrategy;
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.directionFacing = Direction.UP;
        this.posX = 500; //TODO replace with room 1 starting position
        this.posY = 500; //TODO replace with room 1 starting position
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
        Log.d("keyPress", "Applied Vector");
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
            Log.d("keyPress", "Added Vector: " + movementVector.getX() + " " + movementVector.getY());
            updatePosition();
        }
        updateDirection();
    }

    public void removeVector(Vector v) {
        Log.d("keyPress", "Removed Vector");
        if (appliedVectors.remove(v)) {
            movementVector.subtractVector(v);
        }
        updateDirection();
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
    }

    public void setPosition(int x, int y) {
        posX = x;
        posY = y;
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
        MapLayout mapLayout = MapLayout.getInstance();
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, res.getIdentifier(sprite,
                "drawable", context.getPackageName())), mapLayout.getTileSize(), mapLayout.getTileSize(), false);
    }
}
