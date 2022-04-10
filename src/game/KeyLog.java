package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyLog implements KeyListener {
    private HashMap<Integer, Boolean> keys;

    public KeyLog() {
        keys = new HashMap<>();
    }

    public boolean isPressed(int c) {
        try {
            return keys.get(c);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys.put(e.getKeyCode(), true);
    }

    public void keyReleased(KeyEvent e) {
        keys.put(e.getKeyCode(), false);
    }
}
