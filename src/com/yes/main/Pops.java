package com.yes.main;

public class Pops {
    //Genes that all Pops need, X Y and Fitness (the fitness function's result)
    private int x, y, fitness;

    //Constructor Class with all Genes from each Pop
    public Pops(int x, int y, int fitness) {
        this.x = x;
        this.y = y;
        this.fitness = fitness;
    }

    //Getters methods for each Pop Gene to access them from the Lists
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
