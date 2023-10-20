package com.example.cs2340game.model;

public class SprintStrategy implements MovementStrategy {
    @Override
    public void move(Vector movementVector, int[] position) {
        position[0] += (int) (movementVector.getX() * 1.5);
        position[1] += (int) (movementVector.getY() * 1.5);
    }
}