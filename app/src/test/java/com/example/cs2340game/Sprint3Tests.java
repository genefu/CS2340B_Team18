package com.example.cs2340game;

import static org.junit.Assert.*;

import com.example.cs2340game.model.Avatar;
import com.example.cs2340game.model.Model;
import com.example.cs2340game.model.Score;
import com.example.cs2340game.model.MovementStrategies.SprintStrategy;
import com.example.cs2340game.model.MovementStrategies.StandardVectors;
import com.example.cs2340game.model.MovementStrategies.Vector;
import com.example.cs2340game.model.MovementStrategies.WalkStrategy;
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

    @Test
    public void playerMovesUp() {
        int[] pos = new int[2];
        Vector vect = new Vector(3, 4);
        WalkStrategy walk = new WalkStrategy();
        walk.move(vect, pos);

        assertEquals(30,pos[0]);
        assertEquals(40, pos[1]);
    }

    @Test
    public void playerMovesDown() {
        int[] pos = new int[2];
        Vector vect = new Vector(-3, -4);
        WalkStrategy walk = new WalkStrategy();
        walk.move(vect, pos);

        assertEquals(-30,pos[0]);
        assertEquals(-40, pos[1]);
    }

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

    @Test
    public void playerWins() {
        Model model = Model.getInstance();
        model.setScore(new Score("player", 1001, "00:00"));
        assertEquals(true, model.isWinner());
    }

    @Test
    public void playerLoses() {
        Model model = Model.getInstance();
        model.setScore(new Score("player", 999, "00:00"));
        assertEquals(false, model.isWinner());
    }

    @Test
    public void playerMovesRight() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new WalkStrategy());
        avatar.applyVector(StandardVectors.RIGHT_VECTOR);
        assertEquals((int) 1, (int) avatar.getMovementVector().getX());
        assertEquals((int) 0, (int) avatar.getMovementVector().getY());
    }

    @Test
    public void playerMovesLeft() {
        MapLayout mapLayout = MapLayout.getInstance(2);
        Avatar avatar = Avatar.getInstance("sprite3");
        avatar.setMovementStrategy(new WalkStrategy());
        avatar.applyVector(StandardVectors.LEFT_VECTOR);
        assertEquals((int) -1, (int) avatar.getMovementVector().getX());
        assertEquals((int) 0, (int) avatar.getMovementVector().getY());
    }
}
