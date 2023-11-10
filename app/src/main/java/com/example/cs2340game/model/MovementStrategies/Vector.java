package com.example.cs2340game.model.MovementStrategies;

public class Vector implements Comparable<Vector> {
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

    @Override
    public boolean equals(Object o) {
        Vector v = (Vector) o;
        return v.getX() == this.x && v.getY() == this.y;
    }

    @Override
    public int hashCode() {
        return (int) this.x * 100 + (int) this.y;
    }

    @Override
    public int compareTo(Vector v) {
        return (int) (v.getX() - this.x + v.getY() - this.y);
    }
}
