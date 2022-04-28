package main;

import game.Game;
import math.Matrix;
import math.Vector;
import neural_net.ArtificialNeuralNetwork;
import neural_net.TrainNEAT;

public class Main {

    public static void main(String[] args) {
        TrainNEAT train = new TrainNEAT(10, new int[] {2, 5, 5, 5, 1});
        while(true) {
            Game game = new Game(10);
            game.setTrain(train);
            game.startGame();
            train.repopulate();
        }

    }

}
