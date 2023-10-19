package com.example.cs2340game.model;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector() {
        this(0, 0);
    }

    public void addVector(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public void subtractVector(Vector v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public Vector negateVector() {
        return new Vector(-x, -y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
