package ui;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import game.*;
import main.GameClient;

public class FrontObjectLayer {
	protected int num = 0;
	protected GameClient gc = null;
	public List<GameObject> objs = new LinkedList<GameObject>();
	protected Hero player;

	public FrontObjectLayer(int num, GameClient gc) {
		this.gc = gc;
		this.num = num;
	}

	public void draw(Graphics g) {
		makeObj();
		for (int i = 0; i < objs.size(); i++) {
			GameObject obj = objs.get(i);
			if (obj.available == true) {
				obj.draw(g);
			} else if (obj.available == false) {
				objs.remove(i);
			}
		}
	}

	protected void makeObj() {
		if (num == 1) {
			// һ��Ģ����
			objs.add(new Fungus(150, 300, MoveDirection.RIGHT, gc));
			// һ��ʳ�˻�
			objs.add(new Box(250, 450, gc));
			// objs.add(new Chomper(250,420,"Grass",gc));
			// һ�ŵ���
			objs.add(new Grass(300, 380, 3, 2, gc));
			objs.add(new Grass(-100, 430, 20, 6, gc));
			// ���ŵ���
			objs.add(new Grass(600, 430, 20, 6, gc));
			// һ��ש
			objs.add(new Brick(450, 300, 2, 2, Brick.VariantType.V2, gc));
			// ����ש
			objs.add(new Brick(600, 340, 2, 3, Brick.VariantType.V2, gc));
			// һ������
			// objs.add(new Brick(721,300,2,1,"B1",gc));
			objs.add(new Box(780, 300, gc));
			// objs.add(new Brick(809,300,2,1,"B1",gc));
			// һ���ڹ�
			objs.add(new Tortoise(780, 400, MoveDirection.LEFT, gc));
			// һ�Ż�ɫ����
			objs.add(new Brick(870, 400, 1, 1, Brick.VariantType.V1, gc));
			objs.add(new Brick(900, 400 - 30, 1, 2, Brick.VariantType.V1, gc));
			objs.add(new Brick(930, 400 - 30 * 2, 1, 3, Brick.VariantType.V1, gc));
			objs.add(new Brick(960, 400 - 30 * 3, 1, 4, Brick.VariantType.V1, gc));
			objs.add(new Brick(990, 400 - 30 * 4, 1, 5, Brick.VariantType.V1, gc));
			// �����ݼ�ĵذ�
			objs.add(new Brick(990, 400 - 60, 32, 2, Brick.VariantType.V1, gc));
			// һ��Ģ����Ⱥ
			objs.add(new Fungus(1120, 300, MoveDirection.LEFT, gc));
			objs.add(new Fungus(1320, 300, MoveDirection.LEFT, gc));
			objs.add(new Fungus(1520, 300, MoveDirection.LEFT, gc));
			// ���ŵ���
			objs.add(new Grass(1800, 430, 20, 6, gc));
			// ������ɫ����
			objs.add(new Brick(1900, 280, 1, 5, Brick.VariantType.V1, gc));
			objs.add(new Brick(1900 + 30, 280 + 30, 1, 4, Brick.VariantType.V1, gc));
			objs.add(new Brick(1900 + 30 * 2, 280 + 30 * 2, 1, 3, Brick.VariantType.V1, gc));
			objs.add(new Brick(1900 + 30 * 3, 280 + 30 * 3, 1, 2, Brick.VariantType.V1, gc));
			objs.add(new Brick(1900 + 30 * 4, 280 + 30 * 4, 1, 1, Brick.VariantType.V1, gc));
			// һ��ˮ�������ʳ�˻�
			objs.add(new Chomper(2135, 380, "Pipe", gc));
			objs.add(new Pipe(2125, 372, 2, gc));
			// ����ˮ��
			objs.add(new Pipe(2225, 344, 3, gc));
			// ����ˮ����ʳ�˻�
			objs.add(new Chomper(2335, 324, "Pipe", gc));
			objs.add(new Pipe(2325, 316, 4, gc));
			// �ĺ�ˮ��
			objs.add(new Pipe(2525, 316, 14, gc));
			// ���ˮ�ܺ�ʳ�˻�
			// objs.add(new Chomper(2735,324,"Pipe",gc));
			objs.add(new Pipe(2725, 316, 14, gc));
			// �ĺŵ���
			objs.add(new Grass(2900, 430, 20, 6, gc));
			//�Y���Ǳ�
			//objs.add(new Tower(3100, 300, gc));

			System.out.println("Num 1 ��ͼ�Ѿ����������ã��� ");
		}
		num = 0;
	}

	public void reset(int num) {
		objs.removeAll(objs);
		this.num = num;
		makeObj();
	}
}