package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.GameClient;
import ui.background.BackGroundImage;
import ui.events.GameStartEvent;
import ui.layers.BackGroundLayer;

@SuppressWarnings("serial")
public class MainMenuPanel extends AbstractGamePanel{
	
	private JButton play_btn,exit_btn;
	private JLabel bg_label,label_t1;
	private BackGroundLayer bg = new BackGroundLayer(BackGroundLayer.BackgroundType.PLAY_MENU);
	
	private static Map<String, Image> obj_imgs = new HashMap<String, Image>();
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	static {
		Image[] imgs = new Image[] {
				tk.getImage(BackGroundImage.class.getClassLoader().getResource("Img/title1.png")) 
				};
		obj_imgs.put("T1", imgs[0]);
	}

	@Override
	public void removeFromGameClient(GameClient gc) {
		gc.remove(bg_label);
		bg_label.setVisible(false);
	}
	@Override
	public void addToGameClient(GameClient gc) {
		
		bg_label = new JLabel();
		bg_label.setBounds(-5,-26,800,600);
		bg_label.setVisible(true);
		bg_label.setOpaque(false);
		gc.getContentPane().add(bg_label);

		play_btn= new GameButton("START");
		play_btn.setBounds(300,280,224,35);
		play_btn.setContentAreaFilled(false);
		play_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				tellEvent(new GameStartEvent());
			}
		});
		bg_label.add(play_btn);
		
		exit_btn= new GameButton("EXIT");
		exit_btn.setBounds(300,330,224,35);
		exit_btn.setContentAreaFilled(false);
		exit_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		bg_label.add(exit_btn);
	
		label_t1 = new JLabel();
		label_t1.setIcon(new ImageIcon(obj_imgs.get("T1")));
		label_t1.setBounds(265,100,300,147);
		label_t1.setOpaque(false);
		bg_label.add(label_t1);
	}
	
	public void paint(Graphics g) {
		bg.render(g, null);
		bg_label.paintAll(g);
	}

}
