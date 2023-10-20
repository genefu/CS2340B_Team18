package com.example.cs2340game.model;

import com.example.cs2340game.views.MapLayout;

public class StandardVectors {
    public static final Vector UP_VECTOR = new Vector(0, -MapLayout.getInstance().getTileSize());
    public static final Vector DOWN_VECTOR = new Vector(0, MapLayout.getInstance().getTileSize());
    public static final Vector LEFT_VECTOR = new Vector(-MapLayout.getInstance().getTileSize(), 0);
    public static final Vector RIGHT_VECTOR = new Vector(MapLayout.getInstance().getTileSize(), 0);
}
