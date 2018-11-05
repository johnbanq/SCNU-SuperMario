package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import main.GameClient;
import sounds.SoundManager;
import ui.components.GameButton;
import ui.events.GameRestartEvent;
import ui.layers.BackGroundLayer;

@SuppressWarnings("serial")
public class GameOverPanel extends AbstractGamePanel {

	private JButton replay_btn,exit_btn;
	private JLabel bg_label,gameover_label;
	private BackGroundLayer bg = new BackGroundLayer(BackGroundLayer.BackgroundType.GAME_OVER_MENU);
	
	public GameOverPanel(SoundManager sound_mgr) {
		super(sound_mgr);
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
		
		gameover_label = new JLabel("GAME OVER");
		gameover_label.setFont(new Font("GAME OVER", Font.BOLD, 55));
		gameover_label.setForeground(Color.red);// …Ë÷√◊÷ÃÂ—’…´
		gameover_label.setBounds(255, 100, 400, 200);
		gameover_label.setOpaque(false);
		bg_label.add(gameover_label);

		replay_btn = new GameButton("RESTART",getSoundManager());
		replay_btn.setBounds(300, 280, 224, 35);
		replay_btn.setContentAreaFilled(false);
		replay_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				tellEvent(new GameRestartEvent());
			}
		});
		bg_label.add(replay_btn);
		
		exit_btn= new GameButton("EXIT",getSoundManager());
		exit_btn.setBounds(300,330,224,35);
		exit_btn.setContentAreaFilled(false);
		exit_btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		bg_label.add(exit_btn);
	
	}
	
	public void paint(Graphics g) {
		bg.render(g, null);
		bg_label.paint(g);
	}

}
