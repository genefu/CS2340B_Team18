package com.example.cs2340game.model;

import com.example.cs2340game.views.MapLayout;

import java.util.Map;

public class WalkStrategy implements MovementStrategy {
    @Override
    public void move(Vector movementVector, int[] position) {
        MapLayout mapLayout = MapLayout.getInstance();
        position[0] += movementVector.getX() * 64;
        position[1] += movementVector.getY() * 64;
    }
}
