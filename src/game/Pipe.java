package game;

import config.Config;
import math.Vector;
import math.Vector2D;

import javax.swing.*;
import java.awt.*;

public class Pipe extends GameObject {

    private JLabel img;
    private Vector pos;
    private boolean isDead;

    public Pipe(double height) {
        isDead = false;
        pos = new Vector2D(3, height);
        img = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Config.GAME_PIPE.getImage(), 0,
                        Config.PIPE_HEIGHT_PIXELS + Config.PIPE_HOLE_SIZE_PIXELS,
                        Config.PIPE_WIDTH_PIXELS, (2 * Config.PIPE_HEIGHT_PIXELS) + Config.PIPE_HOLE_SIZE_PIXELS, 0, 0,
                        Config.PIPE_IMG_WIDTH_PIXELS, Config.PIPE_IMG_HEIGHT_PIXELS, null);
                g.drawImage(Config.GAME_PIPE.getImage(), 0,
                        Config.PIPE_HEIGHT_PIXELS,
                        Config.PIPE_WIDTH_PIXELS, 0, 0, 0,
                        Config.PIPE_IMG_WIDTH_PIXELS, Config.PIPE_IMG_HEIGHT_PIXELS, null);
            }
        };
    }

    public void update(Game game, long deltaTime) {
        if(isDead) {
            return;
        }
        pos.set(0, pos.get(0) - (Config.PIPE_SPEED * deltaTime / 1000.0));
        if(pos.get(0) < -5) {
            pos.set(0, 5);
            pos.set(1, Math.random() * 5 - 2.5);
        }
        img.setBounds((int)((Config.WINDOW_WIDTH / 2.0) + (pos.get(0) * Config.PIXELS_PER_UNIT) - (Config.PIPE_WIDTH_PIXELS / 2.0)),
                (int)((Config.WINDOW_HEIGHT / 2.0) - (pos.get(1) * Config.PIXELS_PER_UNIT) - Config.PIPE_HEIGHT_PIXELS - (Config.PIPE_HOLE_SIZE_PIXELS / 2.0)),
                Config.PIPE_WIDTH_PIXELS, (Config.PIPE_HEIGHT_PIXELS * 2) + Config.PIPE_HOLE_SIZE_PIXELS);
    }

    public double getHeight() {
        return pos.get(1);
    }

    public boolean isActive(double birdPos) {
        return Math.abs(pos.get(0) - birdPos) < Config.PIPE_WIDTH + Config.BIRD_WIDTH;
    }

    public boolean isDead() {
        return false;
    }

    public JLabel getImage() {
        return img;
    }

}
