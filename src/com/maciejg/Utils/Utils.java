package com.maciejg.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static Boolean[][] convertToTwoDimension(List<Boolean> list) {
        Boolean[][] tab = new Boolean[20][20];
        int counter = 0;
        for(int i = 0; i < 20; i ++){
            for (int j = 0; j < 20; j++) {
                tab[i][j] = list.get(counter);
                counter ++;
            }
        }
        return tab;
    }

    public static List<Boolean> convertToList(Boolean[][] tab) {
        List<Boolean> list = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            for (int j = 0; j < 20; j++) {
                list.add(tab[i][j]);
            }
        }
        return list;
    }

    public static Integer[][] convertToIntegerArray(Boolean[][] booleansTab) {
        Integer[][] tab = new Integer[20][20];
        for(int i = 0; i < 20; i ++){
            for (int j = 0; j < 20; j++) {
                if(booleansTab[i][j] == true) tab[i][j] = 1;
                else tab[i][j] = 0;
            }
        }
        return tab;
    }

    public static Integer[] calculateDistance(int learningX, int learningY, int neuronX, int neuronY) {
        Integer tab[] = new Integer[2];
            //Contains x value of table
            tab[0] = learningX - neuronX;
            //Contains y value of table
            tab[1] = learningY - neuronY;
        return tab;
    }

    public static double calculateDistanceBetweenPoints(int learningX, int learningY, int neuronX, int neuronY) {
        return Math.sqrt((learningX - neuronX)*(learningX - neuronX) + (learningY - neuronY)*(learningY - neuronY));
    }

    public static Boolean checkIfNetworkIsGoodEnough(List learning, List neurons) {
        int counter = 0;
        int max = learning.size() * neurons.size();
        //gdy jest 90% zgodnosci koniec uczenia
        int howMuchNeed = max * (90/100);
        for(int i = 0; i < learning.size(); i++) {
            if(learning.get(i) == neurons.get(i)) counter++;
        }
        if(counter >= howMuchNeed) {
            System.out.println("The end");
            return true;
        }
        else return false;
    }
}
