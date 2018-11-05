package ui.components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import main.GameClient;
import game.Hero;

public class BackGroundImage {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Map<String, Image> back_imgs = new HashMap<String, Image>();

	private String img_name = null;
	private int x = 0, y = 0;
	private final int x_speed = 1;
	private BackGroundImage follow_bg = null;

	static {
		Image[] imgs = new Image[] { 
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/background1.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/background_cloud.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/forest3.2.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu1.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu2.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu3.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu4.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/menu5.png")), };
		back_imgs.put("FOREST", imgs[0]);
		back_imgs.put("CLOUD", imgs[1]);
		back_imgs.put("MENU", imgs[2]);
		back_imgs.put("MAIN1", imgs[3]);
		back_imgs.put("MAIN2", imgs[4]);
		back_imgs.put("MAIN3", imgs[5]);
		back_imgs.put("DIE1", imgs[6]);
		back_imgs.put("DIE2", imgs[7]);

	}

	public BackGroundImage(int x, int y, String img_name) {
		this.x = x;
		this.y = y;
		this.img_name = img_name;
	}

	public void render(Graphics g, Hero player) {
		Image img = back_imgs.get(img_name);
		g.drawImage(img, x, y, null);
		move(player);
		reposition_follower_and_me(follow_bg);
	}
	

	public void move(Hero player) {
		if(img_name == "CLOUD" || img_name == "FOREST") {
			if (player.x + player.speed_x > Hero.LIM_X2 && player.finish == false) {
				//x -= player.speed_x / 4;
			}	
			if (img_name == "CLOUD") {
				x -= x_speed;
			}
		} else {
			x -= x_speed * 2;
		}
	}
	
	private void reposition_follower_and_me(BackGroundImage bg) {
		if (this.x <= -GameClient.window_width) {
			this.x = GameClient.window_width;
		}
		if (bg != null) {
			if (bg.x <= 0 && bg.x >= -GameClient.window_width) {
				this.x = bg.x + GameClient.window_width;
			}
		}
	}

	public void setFollowBg(BackGroundImage bg) {
		this.follow_bg = bg;
	}

}
