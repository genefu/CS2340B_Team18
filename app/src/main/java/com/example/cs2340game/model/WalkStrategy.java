package com.example.cs2340game.model;

public class WalkStrategy implements MovementStrategy {
    @Override
    public void move(Vector movementVector, int[] position) {
        position[0] += movementVector.getX();
        position[1] += movementVector.getY();
    }
}
