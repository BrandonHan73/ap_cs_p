package config;

import math.Vector2D;

import javax.swing.*;

public class Config {

    public static final double ANN_INITIAL_RANDOM_MIN = -1, ANN_INITIAL_RANDOM_MAX = 1;
    public static final double ANN_STANDARD_MUTATION_RATE = 0.01;

    public static final ImageIcon BIRD_IMAGE_ICON = new ImageIcon("src/config/flappy_bird.png");
    public static final double BIRD_WIDTH, BIRD_HEIGHT = 1;
    public static final double BIRD_JUMP_VELOCITY = 5;
    public static final Vector2D GRAVITATIONAL_ACCELERATION = new Vector2D(0, -9.8);
    public static final int BIRD_WIDTH_PIXELS, BIRD_HEIGHT_PIXELS, BIRD_IMG_WIDTH_PIXELS, BIRD_IMG_HEIGHT_PIXELS;

    public static final ImageIcon PIPE_IMAGE_ICON = new ImageIcon("src/config/pipe.png");
    public static final int PIPE_COUNT = 3;
    public static final double PIPE_SPACING, PIPE_OFFSET = 1, PIPE_VARIATION = 4, PIPE_SPEED = 3;
    public static final double PIPE_HOLE_SIZE = 3.5, PIPE_WIDTH = 1.5;
    public static final int PIPE_HOLE_SIZE_PIXELS;
    public static final int PIPE_WIDTH_PIXELS, PIPE_HEIGHT_PIXELS, PIPE_IMG_WIDTH_PIXELS, PIPE_IMG_HEIGHT_PIXELS;

    public static final int GROUND_LEVEL = -4;

    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    public static final double WINDOW_HEIGHT_UNITS = 10, WINDOW_WIDTH_UNITS;
    public static final double PIXELS_PER_UNIT;

    static {
        PIXELS_PER_UNIT = (double)WINDOW_HEIGHT / WINDOW_HEIGHT_UNITS;
        WINDOW_WIDTH_UNITS = WINDOW_WIDTH / PIXELS_PER_UNIT;

        BIRD_IMG_WIDTH_PIXELS = BIRD_IMAGE_ICON.getIconWidth();
        BIRD_IMG_HEIGHT_PIXELS = BIRD_IMAGE_ICON.getIconHeight();
        BIRD_WIDTH = BIRD_HEIGHT * BIRD_IMG_WIDTH_PIXELS / BIRD_IMG_HEIGHT_PIXELS;
        BIRD_WIDTH_PIXELS = (int)(BIRD_WIDTH * PIXELS_PER_UNIT);
        BIRD_HEIGHT_PIXELS = (int)(BIRD_HEIGHT * PIXELS_PER_UNIT);

        PIPE_WIDTH_PIXELS = (int)(PIPE_WIDTH * PIXELS_PER_UNIT);
        PIPE_IMG_WIDTH_PIXELS = PIPE_IMAGE_ICON.getIconWidth();
        PIPE_IMG_HEIGHT_PIXELS = PIPE_IMAGE_ICON.getIconHeight();
        PIPE_HEIGHT_PIXELS = (int)((double)PIPE_IMG_HEIGHT_PIXELS / PIPE_IMG_WIDTH_PIXELS * PIPE_WIDTH_PIXELS);
        PIPE_HOLE_SIZE_PIXELS = (int)(PIPE_HOLE_SIZE * PIXELS_PER_UNIT);
        PIPE_SPACING = (WINDOW_WIDTH_UNITS + (2 * PIPE_OFFSET)) / PIPE_COUNT;
    }

}
