package game;

import config.Config;
import main.Main;
import math.Function;
import math.Vector;
import math.Vector2D;
import neural_net.TrainNEAT;

import javax.swing.*;
import java.awt.*;

public class Game {

    public JFrame window;
    public final KeyLog keyLog;
    public Bird[] objects;
    public Pipe[] pipes;
    private long startTime;

    public final int populationSize;
    private JSlider speedSlider;

    public Game(int popSize) {
        populationSize = popSize;
        objects = new Bird[popSize];
        for(int i = 0; i < objects.length; i++) {
            objects[i] = new Bird(new Vector2D(
                    Config.BIRD_SPAWN_X + Function.randomize.pass(Config.BIRD_SPAWN_X_RANDOM),
                    Config.BIRD_SPAWN_Y + Function.randomize.pass(Config.BIRD_SPAWN_Y_RANDOM)));
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

        speedSlider = new JSlider();
        speedSlider.setMinimum(1);
        speedSlider.setMaximum(Config.MAX_SPEED_MULTIPLIER);
        speedSlider.setValue((int) Config.getSpeedMultiplier());
        speedSlider.addChangeListener(e -> {
            Config.setSpeedMultiplier(speedSlider.getValue());
        });
        speedSlider.setBounds(0, 0, 100, 25);
        window.add(speedSlider);

        for(Bird object : objects) {
            window.add(object.getImage());
        }
        for(Pipe pipe : pipes) {
            window.add(pipe.getImage());
        }

        window.setVisible(true);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setTrain(TrainNEAT train) {
        if(train.populationSize != this.populationSize)
            throw new IllegalArgumentException("Population size must be the same.");
        train.cleanseFitness();
        for(int i = 0; i < populationSize; i++) {
            objects[i].assign(train.getNetworks()[i]);
        }
        for(int i = 1; i < populationSize; i++) {
            objects[i].highlight(new Color(255 - (255/populationSize * i), 255/populationSize * i, 0, 255/populationSize * i));
        }
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

    public double getNextPipeDist(double loc) {
        double shortest = Double.MAX_VALUE;
        for(Pipe pipe : pipes) {
            if(pipe.distanceFrom(loc) < shortest) {
                shortest = pipe.distanceFrom(loc);
            }
        }
        return shortest;
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
        long deltaTime, start = System.currentTimeMillis(), temp;
        boolean temp_;
        while(true) {
            temp = System.currentTimeMillis();
            deltaTime = (temp - start) * Config.getSpeedMultiplier();
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
            for(int i = 0; i < objects.length; i++) {
                Main.dataLabel3[i].setText(objects[i].getFitness() + "");
            }
            temp_ = false;
            for(Bird bird : objects) {
                if(!bird.isDead()) {
                    temp_ = true;
                    break;
                }
            }
            if(!temp_) {
                window.setVisible(false);
                window.dispose();
                break;
            }
        }
    }

}
