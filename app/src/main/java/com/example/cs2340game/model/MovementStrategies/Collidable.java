package com.example.cs2340game.model.MovementStrategies;

import androidx.core.math.MathUtils;

import com.example.cs2340game.views.MapLayout;
import com.example.cs2340game.model.Tile;
import com.example.cs2340game.views.MapLayout;
public interface Collidable {
    enum CollisionBox {
        TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT, NONE
    }

    public CollisionBox checkCollision();

    public void moveToValidPosition(CollisionBox collisionBox);

    // Returns the set of all tiles that the avatar is currently on top of
    public Tile[] getTileCoverage(Tile[][] tileMap);
}
