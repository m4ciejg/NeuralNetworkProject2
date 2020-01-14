package com.maciejg;

import java.util.Random;

public class Neuron {
    private Random random;
    private double [] weights;
    private int numberOfInputs;
    private double delta = 0;

    public Neuron(int numberOfInputs) {
        this.numberOfInputs = numberOfInputs;
        weights = new double[numberOfInputs + 1];
    }

    public void initRandomWeight() {
        random = new Random();
        for(int i = 0; i < numberOfInputs; i++) {
            weights[i] = (random.nextDouble()-0.5) * 2.0 * 0.01;
        }
    }

    public void classifyNeuron() {
       // if (this.weights > )
    }

/*    public int getInputCount() {
        return liczba_wejsc;
    }

    public double oblicz_wyjscie(double [] wejscia){

        double fi=wagi[0];
        for(int i=1;i<=liczba_wejsc;i++)
            fi+=wagi[i]*wejscia[i-1];

        previousSum = fi;
        previousValue = f(fi);

        return previousValue;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void SetDelta(double[] previousLayerProducts) {
        delta = 0.0;

        for (double product : previousLayerProducts) {
            delta += product;
        }
    }

    public double getDeltaMultipliedByNthWeight(int n) {
        return delta*wagi[n];
    }

    public void changeWeights(double[] input) {

        wagi[0] = ETA*delta*f_poch(previousSum);
        for(int i=1;i<wagi.length;i++)
            wagi[i] += ETA*delta*f_poch(previousSum)*input[i-1];

        delta = 0.0;
        previousSum=0;
        previousValue=0;
    }

    private double f(double x) {
        return 1.0/(1.0+Math.exp(-x));
    }

    private double f_poch(double x) {
        return f(x)*(1.0-f(x));
    }*/
}
