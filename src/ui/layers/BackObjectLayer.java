package ui.layers;

import java.awt.Graphics;

import game.Tower;
import main.GameClient;
import ui.GamePanel;

public class BackObjectLayer extends FrontObjectLayer {

	public BackObjectLayer(int num, GamePanel gp) {
		super(num, gp);
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	protected void makeObj() {
		if (num == 1) {
			// ½áÊø³Ç±¤
			objs.add(new Tower(3100, 300, panel));
		}
		num = 0;
	}
}
