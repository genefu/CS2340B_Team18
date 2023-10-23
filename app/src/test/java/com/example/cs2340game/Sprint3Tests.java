package com.example.cs2340game;

import static org.junit.Assert.*;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.SprintStrategy;
import com.example.cs2340game.model.StandardVectors;
import com.example.cs2340game.model.WalkStrategy;
import com.example.cs2340game.views.MapLayout;

import org.junit.Test;

public class Sprint3Tests {

    @Test
    public void sprintStrategyTest() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new SprintStrategy());
        avatar.applyVector(StandardVectors.UP_VECTOR);
        assertEquals(160, avatar.getPosX());
        assertEquals(140, avatar.getPosY());
    }

    @Test
    public void walkStrategyTest() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new WalkStrategy());
        avatar.applyVector(StandardVectors.UP_VECTOR);
        assertEquals(160, avatar.getPosX());
        assertEquals(150, avatar.getPosY());
    }
}
