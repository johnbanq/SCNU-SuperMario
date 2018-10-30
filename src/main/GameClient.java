package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import ui.*;
import ui.events.*;

@SuppressWarnings("serial")
public class GameClient extends JFrame
{
	public enum ClientState{
		MAIN_MENU,GAME,GAME_OVER,GAME_WIN
	}
	
	public final static int window_width = 800, window_height = 600;
	private AbstractGamePanel cur_panel;
	private Image buffer_img;
	
	GameClient() {
		//setup the game window
		this.setTitle("Super Mario");
		this.setBounds(0, 0, window_width, window_height);
		this.setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setLayout(null);
		this.setBackground(Color.cyan);
	}

	public void paint(Graphics g) {
		//setup the dual buffer
		if(buffer_img == null)
			buffer_img = this.createImage(window_width, window_height);
		Graphics buf_g= buffer_img.getGraphics();
		//paint the panel
		cur_panel.paint(buf_g);
		//apply
		g.drawImage(buffer_img, 0, 0, null);
	}

	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(49);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		//init the game client
		GameClient gc = new GameClient();
		gc.switch_to_state(ClientState.MAIN_MENU);
		//start painting
		Thread thread = new Thread(gc.new PaintThread());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void switch_to_state(ClientState state) {
		/*
		 * switch the GameClient between states,eg: from MAIN_MENU to GAME ......
		 * and let panels handle the logic
		 * all panels are constructed here
		 */
		if(cur_panel!=null) {
			cur_panel.removeFromGameClient(this);
		}
		switch(state) {
		case GAME:
			cur_panel = new GamePanel();
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					if(event instanceof GameOverAndCanShowScreen) {
						switch_to_state(ClientState.GAME_OVER);
					}else if(event instanceof GameWonAndCanShowScreen) {
						switch_to_state(ClientState.GAME_WIN);
					}
				}
			});
			break;
		case GAME_OVER:
			cur_panel = new GameOverPanel();
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game restart event
					switch_to_state(ClientState.GAME);
				}
			});
			break;
		case GAME_WIN:
			cur_panel.addToGameClient(this);
			cur_panel = new GameWinPanel(cur_panel);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game restart event
					switch_to_state(ClientState.GAME);
				}
			});
			break;
		case MAIN_MENU:
			cur_panel = new MainMenuPanel();
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game start event
					switch_to_state(ClientState.GAME);
				}
			});
			break;
		default:
			break;
		}
		cur_panel.addToGameClient(this);
	}
}
