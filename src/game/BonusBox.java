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


public class BonusBox extends GameObject 
{
	private static String motto = "艰苦奋斗严谨治学求实创新为人师表";
	private int motto_cnt = 0;
	private GameLabel motto_label;
	
	private static Image[] imgs = ImageLoader.loadImage(tk,ImageName.BOX);
	private FrameSequence frame_seq = new FrameSequence(
			new Image[] {
					imgs[0],
					imgs[0],
					imgs[1],
					imgs[1],
					imgs[2],
					imgs[2],
					imgs[3],
					imgs[3]
			}
	);
	
	protected static final int Y_SPE=10,G_ADD=3;
	protected int yspe=0,in_y=0;

	public BonusBox(int x, int y, GamePanel gp) {
		super(x, y, gp);
		in_y=y;
		obj_w=30;
		obj_h=30;
		all_w=30;
		all_h=30;
		
		motto_label = new GameLabel(getPosX()-25,getPosY()-85,gp);
		JLabel label = motto_label.getLabel();
		label.setVisible(true);
		label.setBackground(Color.RED);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑", 0, 40));
		label.setOpaque(false);
		label.setBounds(0,0,100,100);
		gp.getObjectLayer().objs.add(motto_label);
		
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		Image img = frame_seq.currentFrameAndNext();
		g.drawImage(img, getPosX(), getPosY(), null);
	}
	
	protected void touchWithHero(Hero hero) {
		super.touchWithHero(hero);
		if(hero.live==false) return;
		if(hero.getNextFrameRectangle().intersects(this.getTotalRectangle()))
		{
			if(hero.y>=getPosY()+all_h)
			{
				touch=Action.BUNT;
			}
		}
		else
		{
			touch=Action.UNTOUCH;
		}
		doAction();
	}
	
	protected void doAction(){
		super.doAction();
		if(touch!=Action.UNTOUCH)
			System.out.println(touch.toString());
		if(touch==Action.BUNT)
		{
			yspe=-Y_SPE;
			
			//撞箱子音效
			panel.getSoundManager().playSound(MusicName.撞箱子);
			doMotto();
		}
		else if(touch==Action.UNTOUCH)
		{
			
			if(getPosY()<in_y)
			{
				yspe+=G_ADD;
			}
			if(getPosY()+yspe>in_y)
			{
				setPosY(in_y);
				yspe=0;
			}
		}
		setPosY(getPosY() + yspe);

	}
	
	private void doMotto() {
		if(motto_cnt == motto.length()) {
			motto_label.getLabel().setVisible(false);
			panel.showBonus();
			motto_cnt++;
			return;
		}
		if(motto_cnt<motto.length()) {
			if(motto_cnt == 0) {
				motto_label.getLabel().setVisible(true);
			}
			StringBuffer str = new StringBuffer();
			str.append(motto.charAt(motto_cnt++));
			motto_label.getLabel().setText(str.toString());	
		}
	}

}
