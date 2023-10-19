package com.example.cs2340game.model;

import java.util.HashSet;

public class Avatar {
    private Vector movementVector;
    private HashSet<Vector> appliedVectors;
    private String sprite;
    private int rotation; // Based on the unit circle
    enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    public Avatar(String sprite) {
        movementVector = new Vector();
        appliedVectors = new HashSet<>();
        this.sprite = sprite;
        this.rotation = 90;
    }

    public void applyVector(Vector v) {
        if (appliedVectors.add(v)) {
            movementVector.addVector(v);
        }

    }

    public void removeVector(Vector v) {
        if (appliedVectors.remove(v)) {
            movementVector.subtractVector(v);
        }
    }

    public void clearVectors() {
        appliedVectors.clear();
        movementVector = new Vector();
    }

    public void addRotation(int offset) {
        rotation += offset;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getSprite() {
        return sprite;
    }
}
