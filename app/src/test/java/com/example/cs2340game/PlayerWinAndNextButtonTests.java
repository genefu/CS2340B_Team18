package com.example.cs2340game;

import org.junit.Test;

import static org.junit.Assert.*;


import com.example.cs2340game.model.Model;



public class PlayerWinAndNextButtonTests {
    @Test
    public void playerWinsWhenThresholdMet() {
        Model model = Model.getInstance();
        model.setScore(2000);
        assertEquals(true, model.isWinner() );
    }
    @Test
    public void playerLoseWhenThresholdNodeMet() {
        Model model1 = Model.getInstance();
        model1.setScore(999);
        assertEquals(false, model1.isWinner());
    }
}
