package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import assets.MusicName;
import sounds.SoundManager;
import ui.*;
import ui.events.*;

@SuppressWarnings("serial")
public class GameClient extends JFrame
{
	public enum ClientState{
		MAIN_MENU,INTRO,GAME,GAME_OVER,EPILOGUE,GAME_WIN
	}
	
	public final static int window_width = 800, window_height = 600;
	private AbstractGamePanel cur_panel;
	private GamePanel game_panel;
	private Image buffer_img;
	
	SoundManager sound_mgr = new SoundManager();
	
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
		
		switch_to_state(ClientState.MAIN_MENU);
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
			game_panel = new GamePanel(sound_mgr);
			cur_panel = game_panel;
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					if(event instanceof GameOverAndCanShowScreen) {
						switch_to_state(ClientState.GAME_OVER);
					}else if(event instanceof GameWonAndCanShowEpilogue) {
						switch_to_state(ClientState.EPILOGUE);
					}
					sound_mgr.stopBGM();
				}
			});
			sound_mgr.playBGM(MusicName.”Œœ∑BGM);
			break;
		case INTRO:
			sound_mgr.stopBGM();
			cur_panel = new IntroPanel(sound_mgr);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for press any key event
					if(event instanceof GameStartEvent) {
						switch_to_state(ClientState.GAME);
					}
				}
			});
			break;
		case GAME_OVER:
			cur_panel = new GameOverPanel(sound_mgr);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game restart event
					switch_to_state(ClientState.GAME);
				}
			});
			break;
		case GAME_WIN:
			cur_panel.addToGameClient(this);
			cur_panel = new GameWinPanel(sound_mgr,game_panel);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game restart event
					switch_to_state(ClientState.GAME);
				}
			});
			break;
		case MAIN_MENU:
			cur_panel = new MainMenuPanel(sound_mgr);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for game start event
					if(event instanceof IntroStartEvent)
						switch_to_state(ClientState.INTRO);
				}
			});
			sound_mgr.playBGM(MusicName.≤Àµ•BGM);
			break;
		case EPILOGUE:
			cur_panel = new EpiloguePanel(game_panel,sound_mgr);
			cur_panel.addEventListener(new EventListener() {
				public void actionPerformed(Event event) {
					//listen for epilogue event
					if(event instanceof GameWonAndCanShowScreen) {
						switch_to_state(ClientState.GAME_WIN);	
					}
				}
			});
		default:
			break;
		}
		cur_panel.addToGameClient(this);
	}
}
