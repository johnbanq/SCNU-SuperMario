package ui;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Mario;
import main.GameClient;
import ui.events.GameOverAndCanShowScreen;
import ui.events.GameWonAndCanShowScreen;
import ui.layers.BackGroundLayer;
import ui.layers.BackObjectLayer;
import ui.layers.FrontObjectLayer;

public class GamePanel extends AbstractGamePanel {
	
	public final int window_width = GameClient.window_width,window_height=GameClient.window_height;
	public Mario player1 = new Mario(100, 100, this);
	private FrontObjectLayer obj_layer = new FrontObjectLayer(1, this);
	private BackObjectLayer img_layer = new BackObjectLayer(1, this);
	private BackGroundLayer bg_layer = new BackGroundLayer(BackGroundLayer.BackgroundType.GAME);
	private KeyListener listener;
	
	@Override
	public void removeFromGameClient(GameClient gc) {
		gc.removeKeyListener(listener);
	}

	@Override
	public void addToGameClient(GameClient gc) {
		//fix: so we can recv key input on game!
		gc.requestFocus();
		listener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(player1!=null)//maybe we can do better?
					player1.keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				if(player1!=null)//maybe we can do better?
					player1.keyReleased(e);
			}
		};
		gc.addKeyListener(listener);
	}
	
	public void paint(Graphics g) {
		bg_layer.render(g,player1);
		img_layer.draw(g);
		obj_layer.draw(g);
		player1.draw(g);
		
		if(player1.live == false && player1.y >= 610) {
			tellEvent(new GameOverAndCanShowScreen());
		}else if(player1.finish == true){
			tellEvent(new GameWonAndCanShowScreen());
		}
	}

	public FrontObjectLayer getObjectLayer() {
		return obj_layer;
	}

}
