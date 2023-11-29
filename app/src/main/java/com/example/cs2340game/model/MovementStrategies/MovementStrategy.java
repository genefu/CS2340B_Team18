package com.example.cs2340game.model.MovementStrategies;

public interface MovementStrategy {
    abstract void move(Vector movementVector, int[] position, double speed);
}
