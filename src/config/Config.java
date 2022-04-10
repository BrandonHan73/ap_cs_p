package config;

import math.Vector;
import math.Vector2D;

import javax.swing.*;

public class Config {

    public static final double ANN_INITIAL_RANDOM_MIN = -1, ANN_INITIAL_RANDOM_MAX = 1;
    public static final double ANN_STANDARD_MUTATION_RATE = 0.01;

    public static final ImageIcon GAME_FLAPPY_BIRD = new ImageIcon("src/config/flappy_bird.png");
    public static final int BIRD_WIDTH = 2, BIRD_HEIGHT = 2;
    public static final double BIRD_JUMP_VELOCITY = 5;
    public static final Vector2D GRAVITATIONAL_ACCELERATION = new Vector2D(0, -9.8);
    public static final int BIRD_WIDTH_PIXELS, BIRD_HEIGHT_PIXELS, BIRD_IMG_WIDTH_PIXELS, BIRD_IMG_HEIGHT_PIXELS;

    public static final ImageIcon GAME_PIPE = new ImageIcon("src/config/pipe.png");
    public static final int PIPE_HOLE_SIZE = 5, PIPE_WIDTH = 2;
    public static final double PIPE_SPEED = 1;
    public static final int PIPE_HOLE_SIZE_PIXELS;
    public static final int PIPE_WIDTH_PIXELS, PIPE_HEIGHT_PIXELS, PIPE_IMG_WIDTH_PIXELS, PIPE_IMG_HEIGHT_PIXELS;

    public static final int GROUND_LEVEL = -4;

    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600, WINDOW_HEIGHT_UNITS = 10;
    public static final double PIXELS_PER_UNIT;

    static {
        PIXELS_PER_UNIT = (double)WINDOW_HEIGHT / WINDOW_HEIGHT_UNITS;

        BIRD_WIDTH_PIXELS = (int)(BIRD_WIDTH * PIXELS_PER_UNIT);
        BIRD_HEIGHT_PIXELS = (int)(BIRD_HEIGHT * PIXELS_PER_UNIT);
        BIRD_IMG_WIDTH_PIXELS = GAME_FLAPPY_BIRD.getIconWidth();
        BIRD_IMG_HEIGHT_PIXELS = GAME_FLAPPY_BIRD.getIconHeight();

        PIPE_WIDTH_PIXELS = (int)(PIPE_WIDTH * PIXELS_PER_UNIT);
        PIPE_IMG_WIDTH_PIXELS = GAME_PIPE.getIconWidth();
        PIPE_IMG_HEIGHT_PIXELS = GAME_PIPE.getIconHeight();
        PIPE_HEIGHT_PIXELS = (int)((double)PIPE_IMG_HEIGHT_PIXELS / PIPE_IMG_WIDTH_PIXELS * PIPE_WIDTH_PIXELS);
        PIPE_HOLE_SIZE_PIXELS = (int)(PIPE_HOLE_SIZE * PIXELS_PER_UNIT);
    }

}
