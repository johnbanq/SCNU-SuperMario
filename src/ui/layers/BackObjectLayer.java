package ui.layers;

import java.awt.Graphics;

import game.Tower;
import game.Tower2;
import game.Tower3;
import game.Tower4;
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
			objs.add(new Tower(3000, 345, panel));
			objs.add(new Tower2(100, 345, panel));
			objs.add(new Tower3(700, 345, panel));
			objs.add(new Tower4(1500, 250, panel));
		}
		num = 0;
	}
}
