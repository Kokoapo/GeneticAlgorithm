package com.yes.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static ArrayList<Pops> pops1 = new ArrayList<Pops>();
    private static ArrayList<Pops> pops2 = new ArrayList<Pops>();

    private static int fitnessFunc(int x, int y) {
        return (x * x) - y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String enter = "";

        genPops();
        do {
            for (int i = 0; i < 25; i++) {
                selectPops();
            }

            transferPops();
            enter = scanner.next();
        } while (!enter.toLowerCase().equals("done"));

        scanner.close();
    }

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

    private static void selectPops() {

    }

    private static void transferPops() {

    }
}