package game;

import config.Config;
import math.Vector;

import javax.swing.*;
import java.awt.*;

public class Bird extends GameObject {

    private Vector pos, vel;
    public JLabel img;

    public Bird(int size, int loc) {
        img = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Config.GAME_FLAPPY_BIRD.getImage(), 0, 0, Config.BIRD_WIDTH, Config.BIRD_HEIGHT,
                        0, 0, 600, 600, null);
            }
        };
        img.setBounds(loc, 0, 400, 400);
    }

    public void update(long deltaTime) {
        pos = Vector.add(pos, Vector.mult(vel, deltaTime / 1000.0));
        img.setBounds();
    }

}
