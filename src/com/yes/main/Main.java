package com.yes.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    //List with Main Generation
    private static ArrayList<Pops> pops1 = new ArrayList<Pops>();
    //List to storage Next Generation and pass it to pops1 List later
    private static ArrayList<Pops> pops2 = new ArrayList<Pops>();

    //Fitness function method, returns the fitness for a pop: (x * x) - y
    private static int fitnessFunc(int x, int y) {
        return (x * x) - y;
    }

    //Main method, first it generates the starting 50 pops, each one with X, Y and Fitness
    //then it keeps selecting pops and creating new generations until the user stops it by typing "end"
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String enter = "";

        genPops();
        do {
            for (int i = 0; i < 25; i++) {
                selectPops();
            }

            transferPops();
            enter = scanner.nextLine();
        } while (!enter.toLowerCase().equals("end"));

        scanner.close();
    }

    //Generate First pops Generation
    private static void genPops() {
        Random rand = new Random();
        
        System.out.println("First Generation");
        for (int i = 0; i < 50; i++) {
            int x = rand.nextInt(101);
            int y = rand.nextInt(101);

            pops1.add(new Pops(x, y, fitnessFunc(x, y)));
            System.out.println((i + 1) + " : " + pops1.get(i).getX() + " " + pops1.get(i).getY() + " " + pops1.get(i).getFitness());
        }
    }

    //Selects 5 pops from the Main Generation to be choosen as Parents later, making 2 new pops from the selected Parents
    //repeats the process 25 times to make a whole new 50 pops Generation
    private static void selectPops() {
        ArrayList<Pops> selected = new ArrayList<Pops>();
        Random rand = new Random();
        int total = 0;

        System.out.println("Selected");
        for (int i = 0; i < 5; i++) {
            int index = rand.nextInt(pops1.size());

            selected.add(pops1.get(index));
            total += selected.get(i).getFitness();
            
            System.out.println((i + 1) + " : " + selected.get(i).getX() + " " + selected.get(i).getY() + " " + selected.get(i).getFitness());
        }

        chooseParents(selected, total);
        selected.clear();
    }

    //Makes percentage values for the 5 pops selected previously based on how high their fitness are
    //then selects 2 pops from the 5 selected List to be used as the parents during the crossing over
    private static void chooseParents(ArrayList<Pops> selected, int total) {
        float[] percentages = new float[5];
        float[] percentValue = new float[5];
        ArrayList<Pops> parents = new ArrayList<Pops>();
        Random rand = new Random();
        float totalP = 0;

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
        pops2.add(new Pops(parents.get(0).getX(), parents.get(1).getY(), 
            fitnessFunc(parents.get(0).getX(), parents.get(1).getY())));
        
        pops2.add(new Pops(parents.get(1).getX(), parents.get(0).getY(), 
            fitnessFunc(parents.get(1).getX(), parents.get(0).getY())));
    }

    //Cleans the Main Generation Pops and transfers the Next Generation pops into the Main Generation List
    private static void transferPops() {
        pops1.clear();

        System.out.println("New Generation");
        for (int i = 0; i < pops2.size(); i++) {
            pops1.add(pops2.get(i));
            System.out.println((i + 1) + " : " + pops1.get(i).getX() + " " + pops1.get(i).getY() + " " + pops1.get(i).getFitness());
        }

        pops2.clear();
    }
    
}