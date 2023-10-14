package com.example.cs2340game.views;

import com.example.cs2340game.model.Model;

public class MapLayout {
    private Model model;
    int[][] maplayout;
    public MapLayout () {
        model = Model.getInstance();
        double height = model.getScreenHeight() - model.getScreenHeight()%225; //gameView.getHeight();
        double width = height * 1.7; //gameView.getWidth();
        int numCols = (int) height / 225;
        int numRows = (int)width/225;
        maplayout =
    }
}
