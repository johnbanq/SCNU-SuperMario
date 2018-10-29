package ui;

import java.awt.Graphics;

import game.Tower;
import main.GameClient;

public class ImageMap extends ObjectMap {

	public ImageMap(int num, GameClient gc) {
		super(num, gc);
	}

	public void draw(Graphics g) {
		super.draw(g);
	}

	protected void makeObj() {
		if (num == 1) {
			// 结束城堡
			objs.add(new Tower(3100, 300, gc));

			System.out.println("Num 1 图片已经初始化！ ");
		}
		num = 0;
	}
}
