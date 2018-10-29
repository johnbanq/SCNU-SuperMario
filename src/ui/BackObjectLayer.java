package ui;

import java.awt.Graphics;

import game.Tower;
import main.GameClient;

public class BackObjectLayer extends FrontObjectLayer {

	public BackObjectLayer(int num, GameClient gc) {
		super(num, gc);
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	protected void makeObj() {
		if (num == 1) {
			// ½áÊø³Ç±¤
			objs.add(new Tower(3100, 300, gc));
		}
		num = 0;
	}
}
