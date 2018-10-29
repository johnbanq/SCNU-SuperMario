package ui.background;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import main.GameClient;

public class BackGroundMap2 
{
	
	public enum BackgroundType{
		CLOUD_AND_FOREST,MENU,GAME_OVER
	}
	
	protected GameClient gc = null;
	public Vector<BackGroundImage> objs = new Vector<BackGroundImage>();
	private static Map<String,Image> back_imgs = new HashMap<String,Image>();
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	protected boolean initialize = false;
	
	static 
	{
		Image[] imgs = new Image[]
				{
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/forest1.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/background_cloud.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/forest3.2.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu1.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu2.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu3.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu4.png")),
					tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu5.png")),
				};
		back_imgs.put("FOREST",imgs[0]);
		back_imgs.put("CLOUD",imgs[1]);
		back_imgs.put("MENU",imgs[2]);
		back_imgs.put("MAIN1",imgs[3]);
		back_imgs.put("MAIN2",imgs[4]);
		back_imgs.put("MAIN3",imgs[5]);
		back_imgs.put("DIE1",imgs[6]);
		back_imgs.put("DIE2",imgs[7]);
		
	}
	
	public BackGroundMap2(BackgroundType type,GameClient gc){
		this.gc=gc;
		
		//configure background pics according to type
		switch(type) {
		case CLOUD_AND_FOREST:
			objs.add(new BackGroundImage(0,0,"CLOUD",gc));
			objs.add(new BackGroundImage(800,0,"CLOUD",gc));
			objs.get(0).setFollowBg(objs.get(1));
			objs.get(1).setFollowBg(objs.get(0));
			
			objs.add(new BackGroundImage(0,0,"FOREST",gc));
			objs.add(new BackGroundImage(800,0,"FOREST",gc));
			objs.get(2).setFollowBg(objs.get(3));
			objs.get(3).setFollowBg(objs.get(2));
			break;
		case MENU:
			objs.add(new BackGroundImage(0,0,"MAIN2",gc));
			objs.add(new BackGroundImage(800,0,"MAIN3",gc));
			objs.get(0).setFollowBg(objs.get(1));
			objs.get(1).setFollowBg(objs.get(0));
			break;
		case GAME_OVER:
			objs.add(new BackGroundImage(0,0,"DIE1",gc));
			objs.add(new BackGroundImage(800,0,"DIE2",gc));
			objs.get(0).setFollowBg(objs.get(1));
			objs.get(1).setFollowBg(objs.get(0));
		}	
	}
	
	public void draw(Graphics g){
		BackGroundImage bg = null;
		for(int i=0;i<objs.size();i++)
		{
			bg=objs.get(i);
		    bg.draw(g);
		}
	}

}
