package main;

import game.Game;
import math.Matrix;
import math.Vector;
import neural_net.ArtificialNeuralNetwork;
import neural_net.TrainNEAT;

public class Main {

    public static void main(String[] args) {

        new Game();

        TrainNEAT tn = new TrainNEAT(5, new int[] {2, 5, 7, 7, 5, 1});

        ArtificialNeuralNetwork[] anns = tn.getNetworks();
        anns[0].setFitness(3);
        anns[1].setFitness(4);
        anns[2].setFitness(1);
        anns[3].setFitness(2);
        anns[4].setFitness(5);

        for(ArtificialNeuralNetwork net : tn.getNetworks()) {
            System.out.println(net.pass(new double[] {1, 1})[0]);
        }
        System.out.println("------------");
        tn.repopulate();
        for(ArtificialNeuralNetwork net : tn.getNetworks()) {
            System.out.println(net.pass(new double[] {1, 1})[0]);
        }
        System.out.println("------------");

    }

}
