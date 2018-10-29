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
import main.GameClient;
import sounds.SoundManager;

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
		sound_mgr.playBGM(MusicName.√∞œ’µ∫1);
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
		play_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				tellEvent(new GameStartedEvent());
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
		//label_t1.setIcon(new ImageIcon(obj_imgs.get("T1"))); COMMENTED TO PASS COMPLIATION
		label_t1.setBounds(265,100,300,147);
		label_t1.setOpaque(false);
		bg_label.add(label_t1);
	}
}
