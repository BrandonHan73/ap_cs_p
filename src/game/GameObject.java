package game;

import javax.swing.*;

public abstract class GameObject {
    public abstract void update(long deltaTime);
    public abstract JLabel getImage();
}
