package main;

import config.Config;
import game.Game;
import math.Function;
import math.Matrix;
import math.Vector;
import neural_net.ArtificialNeuralNetwork;
import neural_net.TrainNEAT;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame dataWindow;
    public static JLabel[] dataLabel, dataLabel2;

    public static void main(String[] args) {

        int popSize = 50;

        dataWindow = new JFrame("Data");
        dataWindow.setSize(450, Toolkit.getDefaultToolkit().getScreenSize().height);
        dataWindow.setLayout(null);
        dataWindow.setVisible(true);
        dataWindow.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 450, 0);

        dataLabel = new JLabel[popSize];
        for(int i = 0; i < popSize; i++) {
            dataLabel[i] = new JLabel();
            dataLabel[i].setBounds(0, i * Toolkit.getDefaultToolkit().getScreenSize().height / popSize,
                    225, Toolkit.getDefaultToolkit().getScreenSize().height / popSize);
            dataWindow.add(dataLabel[i]);
        }

        dataLabel2 = new JLabel[popSize];
        for(int i = 0; i < popSize; i++) {
            dataLabel2[i] = new JLabel();
            dataLabel2[i].setBounds(225, i * Toolkit.getDefaultToolkit().getScreenSize().height / popSize,
                    225, Toolkit.getDefaultToolkit().getScreenSize().height / popSize);
            dataWindow.add(dataLabel2[i]);
        }
        for(int i = popSize / 8; i < 2 * popSize / 3; i++) {
            dataLabel[i].setOpaque(true);
            dataLabel2[i].setOpaque(true);
            dataLabel[i].setBackground(Color.GREEN);
            dataLabel2[i].setBackground(Color.GREEN);
        }
        for(int i = 0; i < 2; i++) {
            dataLabel[i].setOpaque(true);
            dataLabel2[i].setOpaque(true);
            dataLabel[i].setBackground(Color.GREEN);
            dataLabel2[i].setBackground(Color.GREEN);
        }

        TrainNEAT train = new TrainNEAT(popSize, new int[] {2, 5, 7, 5, 1});
        while(true) {
            Game game = new Game(popSize);
            game.setTrain(train);
            game.startGame();
            train.repopulate();
        }

    }

}
