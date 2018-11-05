package ui.components;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import assets.MusicName;
import sounds.SoundManager;

@SuppressWarnings("serial")
public class GameButton extends JButton {
	private ImageIcon imgicon = null, rollicon = null, pressedicon = null;
	private SoundManager sound_mgr;
	
	
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Map<String, Image> obj_imgs = new HashMap<String, Image>();
	static {
		Image[] imgs = new Image[] { tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_exit1.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_exit2.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_exit3.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_restart1.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_restart2.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_restart3.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_start1.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_start2.png")),
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/b_start3.png")), };
		obj_imgs.put("BE1", imgs[0]);
		obj_imgs.put("BE2", imgs[1]);
		obj_imgs.put("BE3", imgs[2]);
		obj_imgs.put("BR1", imgs[3]);
		obj_imgs.put("BR2", imgs[4]);
		obj_imgs.put("BR3", imgs[5]);
		obj_imgs.put("BS1", imgs[6]);
		obj_imgs.put("BS2", imgs[7]);
		obj_imgs.put("BS3", imgs[8]);
	}

	public GameButton(String name,SoundManager sound_mgr) {
		this.sound_mgr = sound_mgr;
		// 设置个状态显示的图片
		if (name == "EXIT") {
			imgicon = new ImageIcon(obj_imgs.get("BE1"));
			rollicon = new ImageIcon(obj_imgs.get("BE2"));
			pressedicon = new ImageIcon(obj_imgs.get("BE3"));
		} else if (name == "RESTART") {
			imgicon = new ImageIcon(obj_imgs.get("BR1"));
			rollicon = new ImageIcon(obj_imgs.get("BR2"));
			pressedicon = new ImageIcon(obj_imgs.get("BR3"));
		} else if (name == "START") {
			imgicon = new ImageIcon(obj_imgs.get("BS1"));
			rollicon = new ImageIcon(obj_imgs.get("BS2"));
			pressedicon = new ImageIcon(obj_imgs.get("BS3"));
		}
		super.setIcon(imgicon);
		super.setPressedIcon(pressedicon);
		super.setRolloverIcon(rollicon);
		
		//so we have click sound
		super.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound_mgr.playSound(MusicName.点击);
				System.out.println("点击！");
			}
		});
	}

}
