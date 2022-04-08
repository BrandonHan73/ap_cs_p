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
                g.drawImage(Config.GAME_FLAPPY_BIRD.getImage(), 0, 0, size, size,
                        0, 0, 600, 600, null);
            }
        };
        img.setBounds(loc, 0, 400, 400);
    }

    public void update() {}

}
