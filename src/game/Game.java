package game;

import config.Config;
import math.Vector;

import javax.swing.*;

public class Game {

    public JFrame window;
    public final KeyLog keyLog;
    public GameObject[] objects;
    public Pipe[] pipes;

    public Game() {
        objects = new GameObject[2];
        objects[0] = new Bird(new Vector(2).set(new double[] {1, 1}));
        objects[1] = new Bird(new Vector(2).set(new double[] {-1, -1}));

        pipes = new Pipe[Config.PIPE_COUNT];
        for(int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipe(Config.WINDOW_WIDTH_UNITS + (i * Config.UNITS_PER_PIPE) + Config.PIPE_OFFSET);
        }

        keyLog = new KeyLog();
        window = new JFrame();
        window.setLayout(null);
        window.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        window.addKeyListener(keyLog);

        window.add(objects[0].getImage());
        window.add(objects[1].getImage());
        for(Pipe pipe : pipes) {
            window.add(pipe.getImage());
        }

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
            for(Pipe pipe : pipes) {
                pipe.update(this, deltaTime);
            }
        }
    }

}
