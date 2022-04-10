package game;

import javax.swing.*;

public abstract class GameObject {
    public abstract void update(Game game, long deltaTime);
    public abstract boolean isDead();
    public abstract JLabel getImage();
}
