package game;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import ui.*;
import ui.components.BackGroundImage;
import main.GameClient;

public class Tower4 extends GameObject 
{
	int a = 0;
	protected static Map<String,Image> obj_imgs =  new HashMap<String,Image>();//这个不能放到父类中
	
	static 
	{
		Image[] imgs = new Image []
				{
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/tower4.png")),
				};
		obj_imgs.put("T1", imgs[0]);
	}

	public Tower4(int x, int y,GamePanel gp) {
		super(x, y, gp);
		obj_w=150;
		obj_h=95;
		all_w=150;
		all_h=95;
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(obj_imgs.get("T1"),getPosX(),getPosY(),null);
		touchWithHero(panel.player1);
	}

	protected void action(Hero hero) 
	{
		if(touchhero==Action.BUNT)
		{
			hero.finish();
		}
	}
	protected void touchWithHero(Hero hero) {
		super.touchWithHero(hero);
		if(getTotalRectangle().intersects(hero.getThisFrameRectangle())==true)
		{
			if(a < 3) 
			{
			hero.finish1();
			a++;
			}
			touchhero=Action.BUNT;
			System.out.println("Tower touch hero!");
			
		}
		doAction();
	}
}

