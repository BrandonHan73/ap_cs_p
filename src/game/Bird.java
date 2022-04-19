package game;

import config.Config;
import math.Vector;
import neural_net.ArtificialNeuralNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Bird extends GameObject {

    private Vector pos, vel;
    public JLabel img;
    private boolean isDead, assigned;
    private ArtificialNeuralNetwork neuralNetwork;

    public Bird(Vector startLoc) {
        assigned = false;
        isDead = false;
        pos = startLoc.duplicate();
        vel = new Vector(2).set(new double[] {0, 0});
        img = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Config.BIRD_IMAGE_ICON.getImage(), 0, 0,
                        Config.BIRD_WIDTH_PIXELS, Config.BIRD_HEIGHT_PIXELS, 0, 0,
                        Config.BIRD_IMG_WIDTH_PIXELS, Config.BIRD_IMG_HEIGHT_PIXELS, null);
            }
        };
    }

    public Bird assign(ArtificialNeuralNetwork ann) {
        if(ann.inputCount != 2 || ann.outputCount != 1)
            throw new IllegalArgumentException("Invalid neural network");
        assigned = true;
        neuralNetwork = ann;
        return this;
    }

    public void unassign() {
        assigned = false;
        neuralNetwork = null;
    }

    public void checkDeath(Pipe pipe) {
        if(!pipe.isActive(pos.get(0))) {
            return;
        }
        if(pipe.isActiveVertical(pos.get(1))) {
            return;
        }
        if(!isDead) {
            vel = new Vector(2).set(new double[] {-Config.PIPE_SPEED, 0});
        }
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public JLabel getImage() {
        return img;
    }

    public void update(Game game, long deltaTime) {
        if(!isDead) {
            if(assigned) {
                if(game.keyLog.isPressed(KeyEvent.VK_SPACE)) {
                    vel.set(1, Config.BIRD_JUMP_VELOCITY);
                }
            } else {
                if(game.keyLog.isPressed(KeyEvent.VK_SPACE)) {
                    vel.set(1, Config.BIRD_JUMP_VELOCITY);
                }
            }
            if(pos.get(1) < Config.GROUND_LEVEL) {
                isDead = true;
            }
        }
        vel = Vector.add(vel, Vector.mult(Config.GRAVITATIONAL_ACCELERATION, deltaTime / 1000.0));
        pos = Vector.add(pos, Vector.mult(vel, deltaTime / 1000.0));
        pos.set(1, Math.max(pos.get(1), Config.GROUND_LEVEL));
        img.setBounds((int) ((Config.WINDOW_WIDTH / 2.0) + (pos.get(0) * Config.PIXELS_PER_UNIT) - (Config.BIRD_WIDTH_PIXELS / 2.0)),
                (int) ((Config.WINDOW_HEIGHT / 2.0) - (pos.get(1) * Config.PIXELS_PER_UNIT) - (Config.BIRD_HEIGHT_PIXELS / 2.0)),
                Config.BIRD_WIDTH_PIXELS, Config.BIRD_HEIGHT_PIXELS);
    }

}
