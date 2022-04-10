package game;

import config.Config;
import math.Vector;

import javax.swing.*;
import java.awt.*;

public class Bird extends GameObject {

    private Vector pos, vel;
    public JLabel img;

    public Bird(Vector startLoc) {
        pos = startLoc.duplicate();
        vel = new Vector(2).set(new double[]{0, 0});
        img = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            g.drawImage(Config.GAME_FLAPPY_BIRD.getImage(), 0, 0,
                    Config.BIRD_WIDTH_PIXELS, Config.BIRD_HEIGHT_PIXELS, 0, 0,
                    Config.BIRD_IMG_WIDTH_PIXELS, Config.BIRD_IMG_HEIGHT_PIXELS, null);
            }
        };
        update(0);
    }

    public JLabel getImage() {
        return img;
    }

    public void update(long deltaTime) {
        vel = Vector.add(vel, Vector.mult(Config.GRAVITATIONAL_ACCELERATION, deltaTime / 1000.0));
        pos = Vector.add(pos, Vector.mult(vel, deltaTime / 1000.0));
        img.setBounds((int)((Config.WINDOW_WIDTH / 2.0) + (pos.get(0) * Config.PIXELS_PER_UNIT)),
                (int)((Config.WINDOW_HEIGHT / 2.0) - (pos.get(1) * Config.PIXELS_PER_UNIT)),
                Config.BIRD_WIDTH_PIXELS, Config.BIRD_HEIGHT_PIXELS);
    }

}
