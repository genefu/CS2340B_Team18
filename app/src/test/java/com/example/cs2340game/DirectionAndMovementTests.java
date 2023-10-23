package com.example.cs2340game;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Vector;

public class DirectionAndMovementTests {

    @Test
    public void testCorrectDirection() {
        Avatar avatar = Avatar.getInstance("test");
        Vector v = new Vector(5,-5);
        avatar.setMovementVector(v);
        avatar.updateDirection();
        assertEquals("UP_RIGHT", avatar.getDirectionFacingString());

        Vector v2 = new Vector(5,5);
        avatar.setMovementVector(v2);
        avatar.updateDirection();
        assertEquals("DOWN_RIGHT", avatar.getDirectionFacingString());

        Vector v3 = new Vector(-5,5);
        avatar.setMovementVector(v3);
        avatar.updateDirection();
        assertEquals("DOWN_LEFT", avatar.getDirectionFacingString());

        Vector v4 = new Vector(-5,-5);
        avatar.setMovementVector(v4);
        avatar.updateDirection();
        assertEquals("UP_LEFT", avatar.getDirectionFacingString());

    }

    @Test
    public void testVectorMovement() {
        Vector vector = new Vector(5,0);
        vector.addVector(vector);
        assertEquals(10,vector.getX(),0);
        Vector vector2 = new Vector(0,5);
        vector2.addVector(vector2);
        assertEquals(10,vector2.getY(),0);
        Vector vector4 = new Vector(0,0);
        vector4.addVector(vector4);
        assertEquals(0,vector4.getX(),0);
    }

}
