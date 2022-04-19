package game;

import config.Config;
import math.Vector;
import math.Vector2D;

import javax.swing.*;

public class Game {

    public JFrame window;
    public final KeyLog keyLog;
    public Bird[] objects;
    public Pipe[] pipes;

    public final int populationSize;

    public Game(int popSize) {
        populationSize = popSize;
        objects = new Bird[popSize];
        for(int i = 0; i < objects.length; i++) {
            objects[i] = new Bird(new Vector2D(-4 + i, 0));
        }

        pipes = new Pipe[Config.PIPE_COUNT];
        for(int i = 0; i < pipes.length; i++) {
            pipes[i] = new Pipe(Config.WINDOW_WIDTH_UNITS + (i * Config.PIPE_SPACING) + Config.PIPE_OFFSET);
        }

        keyLog = new KeyLog();
        window = new JFrame();
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        window.addKeyListener(keyLog);

        for(Bird object : objects) {
            window.add(object.getImage());
        }
        for(Pipe pipe : pipes) {
            window.add(pipe.getImage());
        }

        window.setVisible(true);
    }

    public double getNextPipeHeight(double loc) {
        double shortest = Double.MAX_VALUE;
        Pipe wanted = null;
        for(Pipe pipe : pipes) {
            if(pipe.distanceFrom(loc) < shortest) {
                shortest = pipe.distanceFrom(loc);
                wanted = pipe;
            }
        }
        assert wanted != null;
        return wanted.getHeight();
    }

    public void startGame() {
        long deltaTime, start = System.currentTimeMillis(), temp;
        while(true) {
            temp = System.currentTimeMillis();
            deltaTime = temp - start;
            start = temp;
            for(Pipe pipe : pipes) {
                pipe.update(this, deltaTime);
            }
            for (Bird object : objects) {
                for(Pipe pipe : pipes) {
                    object.checkDeath(pipe);
                }
                object.update(this, deltaTime);
            }
        }
    }

}
