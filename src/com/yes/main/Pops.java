package com.yes.main;

public class Pops {
    private int x, y, fitness;

    public Pops(int x, int y, int fitness) {
        this.x = x;
        this.y = y;
        this.fitness = fitness;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
