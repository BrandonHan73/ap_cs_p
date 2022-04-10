package game;

import config.Config;
import math.Vector;

import javax.swing.*;

public class Game {

    public JFrame window;
    public final KeyLog keyLog;
    public GameObject[] objects;

    public Game() {
        objects = new GameObject[3];
        objects[0] = new Bird(new Vector(2).set(new double[] {1, 1}));
        objects[1] = new Bird(new Vector(2).set(new double[] {-1, -1}));
        objects[2] = new Pipe();

        keyLog = new KeyLog();
        window = new JFrame();
        window.setLayout(null);
        window.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        window.addKeyListener(keyLog);

        window.add(objects[0].getImage());
        window.add(objects[1].getImage());
        window.add(objects[2].getImage());

        window.setVisible(true);
    }

    public void startGame() {
        long deltaTime, start = System.currentTimeMillis(), temp;
        while(true) {
            temp = System.currentTimeMillis();
            deltaTime = temp - start;
            start = temp;
            for (GameObject object : objects) {
                object.update(this, deltaTime);
            }
        }
    }

}
