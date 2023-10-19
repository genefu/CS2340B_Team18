package com.example.cs2340game.views;

import com.example.cs2340game.model.Model;

public class MapLayout {
    private Model model;
    private int viewWidth;
    private int viewHeight;
    private int[][] mapLayout;
    public MapLayout(String screen) {
        model = Model.getInstance();
        if (model.getScreenHeight() > model.getScreenWidth()) {
            viewWidth = model.getScreenWidth() - model.getScreenHeight() % 64;
            viewHeight = (int) (viewWidth * 0.588);
        }
        if (model.getScreenHeight() < model.getScreenWidth()) {
            viewHeight = model.getScreenHeight() - model.getScreenHeight() % 64; //gameView.getHeight();
            viewWidth = (int) (viewHeight * 1.7); //gameView.getWidth();
        }
        int numCols = (int) viewHeight / 64;
        int numRows = (int) viewWidth / 64;
        switch (screen) {
        case "1":
            mapLayout = new int[][]
                {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3},
                {3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 3, 3, 1, 1,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 3, 3},
                {3, 2, 1, 3, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 1,
                        1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3},
                {3, 2, 1, 3, 3, 3, 3, 1, 1, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 1, 1, 1, 2, 2, 2, 1, 3, 3, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 3, 1, 1, 1, 2, 2, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 1, 3, 2, 3, 1, 2, 2, 1, 1, 2, 3,
                        3, 1, 3, 3, 1, 3, 3, 2, 2, 3, 3, 1, 2, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 2, 2, 1, 1, 1, 1, 2, 3, 1, 2, 2,
                        2, 3, 1, 1, 1, 1, 1, 1, 2, 3, 3, 1, 2, 3, 3, 2, 3, 3},
                {3, 2, 1, 3, 3, 2, 3, 3, 1, 1, 3, 2, 2, 1, 1, 1,
                        1, 1, 1, 1, 1, 3, 3, 3, 2, 3, 3, 1, 2, 3, 3, 2, 3, 3},
                {3, 2, 1, 2, 2, 3, 3, 3, 3, 1, 1, 1, 2, 3, 3, 3,
                        1, 1, 1, 3, 1, 1, 1, 1, 2, 3, 3, 2, 3, 3, 3, 2, 3, 3},
                {3, 2, 1, 2, 1, 3, 3, 3, 3, 3, 1, 1, 1, 2, 3, 3,
                        1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 2, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 2, 1, 3, 3, 3, 3, 3, 3, 3, 1, 2, 2, 2,
                        3, 3, 1, 3, 1, 2, 2, 1, 3, 3, 1, 2, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 2, 2, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 2, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 1, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3},
                {3, 2, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 3, 3, 3, 3,
                        3, 3, 3, 3, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3},
                {3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
            break;
        case "2":
            mapLayout = new int[][]
                {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                        1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3},
                {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
                        1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                {3, 1, 2, 2, 2, 2, 2, 3, 3, 2, 2, 2, 2, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 1,
                        1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
            break;
        case "3":
            mapLayout = new int[][]
                {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2,
                        2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 3},
                {3, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 3},
                {3, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2,
                        2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 3},
                {3, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2,
                        2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
                {3, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
                {3, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1,
                        1, 1, 1, 1, 2, 2, 3, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 3},
                {3, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                        1, 1, 1, 1, 3, 3, 3, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 3},
                {3, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 3},
                {2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 3},
                {2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1,
                        1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 3},
                {2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2,
                        2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2,
                        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
            break;
        default:
            break;
        }
    }

    public int[][] getMapLayout() {
        return mapLayout;
    }
    public int getViewWidth() {
        return viewWidth;
    }
    public int getViewHeight() {
        return viewHeight;
    }
}