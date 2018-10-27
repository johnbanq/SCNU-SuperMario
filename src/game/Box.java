package game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import assets.ImageLoader;
import assets.ImageName;
import ui.*;
import main.GameClient;


public class Box extends GameObject 
{

	protected static final int Y_SPE=10,G_ADD=3;
	protected int yspe=0,in_y=0;
	protected static Map<String,Image> obj_imgs =  new HashMap<String,Image>();
	protected int d_time=1;
	protected Mushroom mush = null;
	
	static 
	{
		obj_imgs.put("B1", ImageLoader.loadImage(tk,ImageName.BOX_V1));
		obj_imgs.put("B2", ImageLoader.loadImage(tk,ImageName.BOX_V2));
		obj_imgs.put("B3", ImageLoader.loadImage(tk,ImageName.BOX_V3));
		obj_imgs.put("B4", ImageLoader.loadImage(tk,ImageName.BOX_V4));
	}

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
		Image img = null;
		if(d_time<=2)
		{
			img=obj_imgs.get("B1");
			d_time++;
		}
		else if(d_time>2&&d_time<=4)
		{
			img=obj_imgs.get("B2");
			d_time++;
		}
		else if(d_time>4&&d_time<=6)
		{
			img=obj_imgs.get("B3");
			d_time++;
		}
		else if(d_time>6&&d_time<=8)
		{
			img=obj_imgs.get("B4");
			d_time=1;
		}
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
		action();
	}
	
	protected void action()
	{
		super.action();
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
