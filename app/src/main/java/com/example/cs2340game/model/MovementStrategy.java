package com.example.cs2340game.model;

public interface MovementStrategy {
    abstract void move(Vector movementVector, int[] position);
}
