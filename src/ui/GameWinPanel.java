package ui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.GameClient;
import ui.background.BackGroundImage;
import ui.events.GameRestartEvent;
import ui.layers.BackGroundLayer;

@SuppressWarnings("serial")
public class GameWinPanel extends AbstractGamePanel {
	
	private JButton replay_btn,exit_btn;
	private JLabel bg_label,win_label;
	private AbstractGamePanel game_panel;//a small trick to display over it
	
	public GameWinPanel(AbstractGamePanel game_panel) {
		this.game_panel = game_panel;
	}
	
	public void removeFromGameClient(GameClient gc) {
		gc.remove(bg_label);
		bg_label.setVisible(false);
	}
	
	public void addToGameClient(GameClient gc) {
		
		bg_label = new JLabel();
		bg_label.setBounds(-5, -26, 800, 600);
		bg_label.setBackground(Color.blue);
		bg_label.setVisible(true);
		bg_label.setOpaque(false);
		gc.getContentPane().add(bg_label);
		
		win_label = new JLabel("YOU WIN");
		win_label.setFont(new Font("YOU WIN", Font.BOLD, 55));
		win_label.setForeground(Color.red);// …Ë÷√◊÷ÃÂ—’…´
		win_label.setBounds(285, 130, 300, 150);
		win_label.setOpaque(false);
		bg_label.add(win_label);

		replay_btn = new GameButton("RESTART");
		replay_btn.setBounds(300, 280, 224, 35);
		replay_btn.setContentAreaFilled(false);
		replay_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				tellEvent(new GameRestartEvent());
			}
		});
		bg_label.add(replay_btn);
		
		exit_btn= new GameButton("EXIT");
		exit_btn.setBounds(300,330,224,35);
		exit_btn.setContentAreaFilled(false);
		exit_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		bg_label.add(exit_btn);
	
	}
	
	public synchronized void paint(Graphics g) {
		this.game_panel.paint(g);
		bg_label.paint(g);
	}

}
