package ui.background;

import java.awt.Graphics;
import game.Hero;

public interface PlayerAwareLayer{
	abstract void render(Graphics g,Hero player);
}

