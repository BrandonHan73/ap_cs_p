package game;

import config.Config;
import math.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    public JFrame window;
    public JLabel label;
    public GameObject[] objects;

    public Game() {
        objects = new GameObject[2];
        objects[0] = new Bird(new Vector(2).set(new double[] {1, 0}));
        objects[1] = new Bird(new Vector(2).set(new double[] {-1, 0}));

        window = new JFrame();
        window.setLayout(null);
        window.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        window.add(objects[0].getImage());
        window.add(objects[1].getImage());

        window.setVisible(true);
    }

    public void startGame() {
        long deltaTime, start = System.currentTimeMillis(), temp;
        while(true) {
            temp = System.currentTimeMillis();
            deltaTime = temp - start;
            start = temp;
            for (GameObject object : objects) {
                object.update(deltaTime);
            }
        }
    }

}
