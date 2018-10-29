package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import assets.MusicName;
import assets.MusicNameByFunction;
import main.GameClient;
import sounds.GameAudio;
import sounds.Mp3Thread;
import sounds.SoundManager;
import ui.Menu.ButtonListener;

public class MainMenuFrame extends JFrame{
	
	private GameClient gc;
	private JButton play_btn,exit_btn;
	private JLabel bg_label,label_t1;
	private SoundManager sound_mgr;
	
	private LinkedList<EventListener> listeners = new LinkedList<>();
	
	public MainMenuFrame(GameClient gc,SoundManager sound_mgr) {
		this.gc = gc;
		this.sound_mgr = sound_mgr;
		setup_frame();
	}
	
	public void addEventListener(EventListener listener) {
		listeners.addLast(listener);
	}
	
	public void removeEventListener(EventListener listener) {
		listeners.remove(listener);
	}
	
	private void tellEvent(Event e) {
		for(EventListener l:listeners) {
			l.actionPerformed(e);
		}
	}
	
	private void setup_frame() {
		
		bg_label = new JLabel();
		bg_label.setBounds(-5,-26,800,600);
		bg_label.setBackground(Color.red);
		bg_label.setVisible(true);
		bg_label.setOpaque(false);
		getContentPane().add(bg_label);
		
		play_btn= new GameButton("START");
		play_btn.setBounds(300,280,224,35);
		play_btn.setContentAreaFilled(false);
		play_btn.addActionListener(new GameButtonListener(){
			public void actionPerformed(ActionEvent event) {
				super.actionPerformed(event);
				tellEvent(new GameStartedEvent());
			}
		});
		bg_label.add(play_btn);
		
		exit_btn= new GameButton("EIXT");
		exit_btn.setBounds(300,330,224,35);
		exit_btn.setContentAreaFilled(false);
		exit_btn.addActionListener(new GameButtonListener(){
			public void actionPerformed(ActionEvent event) {
				super.actionPerformed(event);
				System.exit(0);
			}
		});
		bg_label.add(exit_btn);
	
		label_t1 = new JLabel();
		//label_t1.setIcon(new ImageIcon(obj_imgs.get("T1"))); COMMENTED TO PASS COMPLIATION
		label_t1.setBounds(265,100,300,147);
		label_t1.setOpaque(false);
		bg_label.add(label_t1);
		
		//bgm1 = new BackGroundMap(2,gc); COMMENTED TO PASS COMPLIATION
		//bgm2 = new BackGroundMap(3,gc); COMMENTED TO PASS COMPLIATION
	}
	
	private class GameButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			new GameAudio("µã»÷").start();
			System.out.println("µã»÷£¡");
		}
	}
}
