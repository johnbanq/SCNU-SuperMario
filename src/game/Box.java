package game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import assets.ImageLoader;
import assets.ImageName;
import ui.*;
import util.FrameSequence;
import main.GameClient;
import sounds.GameAudio;


public class Box extends GameObject 
{
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
	protected Mushroom mush = null;
	
	public Box(int x, int y, GameClient gc) {
		super(x, y, gc);
		in_y=y;
		obj_w=30;
		obj_h=30;
		all_w=30;
		all_h=30;
	}

	public void draw(Graphics g)
	{
		super.draw(g);
		Image img = frame_seq.currentFrameAndNext();
		g.drawImage(img, getPosX(), getPosY(), null);
		if(mush!=null)
			mush.draw(g);
	}
	
	protected void touchWithHero(Hero hero) {
		super.touchWithHero(hero);
		if(hero.live==false) return;
		if(hero.getNextRectangle().intersects(this.getTotalRectangle()))
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
			if(mush==null)
			mush = new Mushroom(getPosX()+obj_w/5,getPosY()-25,this,gc);
			
			//×²Ïä×ÓÒôÐ§
			new GameAudio("×²Ïä×Ó").start();
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

}
