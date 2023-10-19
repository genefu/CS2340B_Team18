package com.example.cs2340game.model;

import java.util.HashSet;

public class Avatar {
    private Vector movementVector;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private Direction directionFacing;
    private int posX;
    private int posY;
    enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    public Avatar(String sprite) {
        movementVector = new Vector();
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.directionFacing = Direction.UP;
        this.posX = 100; //TODO replace with room 1 starting position
        this.posY = 100; //TODO replace with room 1 starting position
    }

    public void applyVector(Vector v) {
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
        }
        updateDirection();
    }

    public void removeVector(Vector v) {
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
        double y = movementVector.getY();
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

    public Direction getDirectionFacing() {
        return directionFacing;
    }
}
