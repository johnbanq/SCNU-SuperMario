package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import assets.ImageLoader;
import assets.ImageName;
import assets.MusicName;
import ui.*;
import util.FrameSequence;
import main.GameClient;


public class GameLabel extends GameObject 
{
	private JLabel label = new JLabel();

	public GameLabel(int x, int y, GamePanel gp) {
		super(x, y, gp);
		obj_w=30;
		obj_h=30;
		all_w=30;
		all_h=30;
	}
	
	public JLabel getLabel() {
		return label;
	}

	public void draw(Graphics g)
	{
		if(label.isVisible()) {
			super.draw(g);
			Graphics g2 = g.create(getPosX(),getPosY(),label.getWidth(), label.getHeight());
			label.paint(g2);	
		}
	}

}
