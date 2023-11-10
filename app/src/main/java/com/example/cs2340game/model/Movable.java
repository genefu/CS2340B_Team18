package com.example.cs2340game.model;

public interface Movable {
    enum Direction {
        UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT
    }

    public void applyVector(Vector v);

    public void removeVector(Vector v);

    public void clearVectors();

    public void updateDirection();

    public void setMovementStrategy(MovementStrategy movementStrategy);

    public void updatePosition();
}
