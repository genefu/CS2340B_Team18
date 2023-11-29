package com.example.cs2340game.model.MovementStrategies;

public class SprintStrategy implements MovementStrategy {
    @Override
    public void move(Vector movementVector, int[] position, double speed) {
        position[0] += (int) (movementVector.getX() * 20 * speed);
        position[1] += (int) (movementVector.getY() * 20 * speed);
    }
}