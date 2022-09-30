package com.yes.main;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    //List with Main Generation
    private static ArrayList<Pops> mainPops = new ArrayList<Pops>();
    //List to storage Next Generation and pass it to mainPops List later
    private static ArrayList<Pops> nextPops = new ArrayList<Pops>();

    //Fitness function method, returns the fitness for a pop: (x * x) - y
    private static int fitnessFunc(int x, int y) {
        return (x * x) - y;
    }

    //Main method, first it generates the starting 50 pops, each one with X, Y and Fitness
    //then it keeps selecting pops and creating new generations until it reaches the 100th Generation
    public static void main(String[] args) {
        int genCount = 1;

        genPops();
        do {
            
            for (int i = 0; i < 25; i++) {
                selectPops();
            }

            transferPops();

            System.out.println("Generation " + genCount);
            genCount++;
        } while (genCount <= 100);
    }

    //Generate First pops Generation
    private static void genPops() {
        Random rand = new Random();
        
        System.out.println("First Generation");
        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(101);
            int y = rand.nextInt(101);

            mainPops.add(new Pops(x, y, fitnessFunc(x, y)));
            System.out.println((i + 1) + " : " + mainPops.get(i).getX() + " " + mainPops.get(i).getY() + " " + mainPops.get(i).getFitness());
        }
    }

    //Selects 5 pops from the Main Generation to be choosen as Parents later, making 2 new pops from the selected Parents
    //repeats the process 25 times to make a whole new 50 pops Generation
    private static void selectPops() {
        ArrayList<Pops> selected = new ArrayList<Pops>();
        Random rand = new Random();

        System.out.println("Selected");
        for (int i = 0; i < 5; i++) {
            int index = rand.nextInt(mainPops.size());

            selected.add(mainPops.get(index));
            
            System.out.println((i + 1) + " : " + selected.get(i).getX() + " " + selected.get(i).getY() + " " + selected.get(i).getFitness());
        }

        chooseParents(selected);
        selected.clear();
    }

    //Makes percentage values for the 5 pops selected previously based on how high their fitness are
    //then selects 2 pops from the 5 selected List to be used as the parents during the crossing over
    private static void chooseParents(ArrayList<Pops> selected) {
        float[] percentages = new float[5];
        float[] percentValue = new float[5];
        ArrayList<Pops> parents = new ArrayList<Pops>();
        Random rand = new Random();
        float totalP = 0;
        int increase = 0;
        boolean needIncrease = false;
        int total = 0;

        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i).getFitness() < 0) {
                int posFitness = Math.negateExact(selected.get(i).getFitness());
                if (increase < posFitness) {
                    increase = posFitness;
                }
                needIncrease = true;

                System.out.println("Needed Increase!");
                System.out.println(posFitness);
            }
        }

        for (int i = 0; i < selected.size(); i++) {
            if (needIncrease) {
                selected.get(i).setFitness(selected.get(i).getFitness() + increase);
            }

            total += selected.get(i).getFitness();
        }

        System.out.println(total);
        for (int i = 0; i < percentages.length; i++) {
            percentValue[i] = ((float)selected.get(i).getFitness() / total) * 100;
            totalP += percentValue[i];
            percentages[i] = totalP;

            System.out.println(totalP + "%");
        }

        for (int i = 0; i < 2; i++) {
            float parentP = rand.nextFloat(100);
            System.out.println(parentP);

            for (int j = 0; j < percentages.length; j++) {
                if ((parentP > percentages[j] - percentValue[j]) && (parentP < percentages[j])) {
                    parents.add(selected.get(j));

                    System.out.println("Parent " + (i + 1) + " : " + parents.get(i).getFitness());
                }
            }
        }

        crossingOver(parents);
        parents.clear();
    }

    //Creates 2 new pops from the Parents selected previously and storage them on the Next Generation List
    private static void crossingOver(ArrayList<Pops> parents) {
        Random rand = new Random();
        int sex = rand.nextInt(100);

        for (int i = 0; i < parents.size(); i++) {
            int mutationX = rand.nextInt(100);
            int mutationY = rand.nextInt(100);

            if (mutationX >= 95) {
                System.out.println("X Muted!");
                parents.get(i).setX(rand.nextInt(101));
            }

            if (mutationY >= 95) {
                System.out.println("Y Muted!");
                parents.get(i).setY(rand.nextInt(101));
            }
        }

        if (sex >= 70) {
            nextPops.add(new Pops(parents.get(0).getX(), parents.get(1).getY(), 
                fitnessFunc(parents.get(0).getX(), parents.get(1).getY())));
            
            nextPops.add(new Pops(parents.get(1).getX(), parents.get(0).getY(), 
                fitnessFunc(parents.get(1).getX(), parents.get(0).getY())));
        } else {
            nextPops.add(new Pops(parents.get(0).getX(), parents.get(0).getY(), 
                fitnessFunc(parents.get(0).getX(), parents.get(0).getY())));
        
            nextPops.add(new Pops(parents.get(1).getX(), parents.get(1).getY(), 
                fitnessFunc(parents.get(1).getX(), parents.get(1).getY())));
        }
    }

    //Cleans the Main Generation Pops and transfers the Next Generation pops into the Main Generation List
    private static void transferPops() {
        mainPops.clear();

        System.out.println("New Generation");
        for (int i = 0; i < nextPops.size(); i++) {
            mainPops.add(nextPops.get(i));
            System.out.println((i + 1) + " : " + mainPops.get(i).getX() + " " + mainPops.get(i).getY() + " " + mainPops.get(i).getFitness());
        }

        nextPops.clear();
    }
}