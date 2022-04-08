package game;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    public JFrame window;
    public JLabel label;
    public GameObject[] objects;

    public Game() {
        window = new JFrame();
        window.setLayout(null);
        window.setSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        window.add(new Bird(100, 0).img);
        window.add(new Bird(400, 100).img);

        window.setVisible(true);
    }

    private void startGame(int populationSize) {
    }

}
