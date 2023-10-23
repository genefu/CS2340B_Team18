package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;

import static java.nio.file.Files.move;

import com.example.cs2340game.model.Player;
import com.example.cs2340game.model.Vector;
import com.example.cs2340game.model.WalkStrategy;


public class PlayerMoveUpAndDown {

    @Test
    public void playerMovesUp() {
        int[] pos = new int[2];
        Vector vect = new Vector(3, 4);
        WalkStrategy walk = new WalkStrategy();
        walk.move(vect, pos);

        assertEquals(30,pos[0]);
        assertEquals(40, pos[1]);
    }

    public void playerMovesDown() {
        int[] pos = new int[2];
        Vector vect = new Vector(-3, -4);
        WalkStrategy walk = new WalkStrategy();
        walk.move(vect, pos);

        assertEquals(-30,pos[0]);
        assertEquals(-40, pos[1]);
    }
}

