package ui.layers;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import game.Hero;
import main.GameClient;
import ui.components.BackGroundImage;

public class BackGroundLayer 
{
	
	public enum BackgroundType{
		GAME,PLAY_MENU,GAME_OVER_MENU
	}
	
	private Vector<BackGroundImage> bg_imgs = new Vector<BackGroundImage>();
	
	public BackGroundLayer(BackgroundType type){
		//configure background pics according to type
		switch(type) {
		case GAME:
			bg_imgs.add(new BackGroundImage(0,0,"CLOUD"));
			bg_imgs.add(new BackGroundImage(800,0,"CLOUD"));
			bg_imgs.get(0).setFollowBg(bg_imgs.get(1));
			bg_imgs.get(1).setFollowBg(bg_imgs.get(0));
			
			bg_imgs.add(new BackGroundImage(0,0,"FOREST"));
			bg_imgs.add(new BackGroundImage(800,0,"FOREST"));
			bg_imgs.get(2).setFollowBg(bg_imgs.get(3));
			bg_imgs.get(3).setFollowBg(bg_imgs.get(2));
			break;
		case PLAY_MENU:
			bg_imgs.add(new BackGroundImage(0,0,"MAIN2"));
			bg_imgs.add(new BackGroundImage(800,0,"MAIN3"));
			bg_imgs.get(0).setFollowBg(bg_imgs.get(1));
			bg_imgs.get(1).setFollowBg(bg_imgs.get(0));
			break;
		case GAME_OVER_MENU:
			bg_imgs.add(new BackGroundImage(0,0,"DIE1"));
			bg_imgs.add(new BackGroundImage(800,0,"DIE2"));
			bg_imgs.get(0).setFollowBg(bg_imgs.get(1));
			bg_imgs.get(1).setFollowBg(bg_imgs.get(0));
		}	
	}
	
	public void render(Graphics g,Hero player){
		BackGroundImage bg = null;
		for(int i=0;i<bg_imgs.size();i++)
		{
			bg=bg_imgs.get(i);
		    bg.render(g,player);
		}
	}

}
