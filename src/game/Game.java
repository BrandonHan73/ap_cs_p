package game;

import config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    public JFrame window;

    public Game() {
        window = new JFrame();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLayout(null);
        window.setUndecorated(true);
        window.setVisible(true);

        JSlider slider = new JSlider();
        slider.setBounds(window.getWidth() / 3, window.getHeight() / 2,
                window.getWidth() / 3, 50);
        slider.setMinimum(10);
        slider.setMaximum(150);
        window.add(slider);

        JButton button = new JButton();
        button.setBounds(3 * window.getWidth() / 8, 2 * window.getHeight() / 3,
                window.getWidth() / 4, 50);
        button.setText("CONTINUE");
        button.addActionListener(e -> {
            startGame(slider.getValue());
        });
        window.add(button);
    }

    private void startGame(int populationSize) {
        for(Component c : window.getComponents()) {
            window.remove(c);
        }
        JLabel l = new JLabel();
        l.setIcon(Config.GAME_FLAPPY_BIRD);
        window.add(l);
        l.setBounds(0, 0, 1000, 1000);
    }

}
