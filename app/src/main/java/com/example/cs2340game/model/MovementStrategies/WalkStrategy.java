package com.example.cs2340game.model.MovementStrategies;

public class WalkStrategy implements MovementStrategy {
    @Override
    public void move(Vector movementVector, int[] position) {
        position[0] += (int) (movementVector.getX() * 10);
        position[1] += (int) (movementVector.getY() * 10);
    }
}
