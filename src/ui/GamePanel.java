package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import assets.ImageLoader;
import assets.ImageName;
import game.Mario;
import main.GameClient;
import sounds.SoundManager;
import ui.components.BlinkingLabel;
import ui.events.GameOverAndCanShowScreen;
import ui.events.GameWonAndCanShowEpilogue;
import ui.events.GameWonAndCanShowScreen;
import ui.layers.BackGroundLayer;
import ui.layers.BackObjectLayer;
import ui.layers.FrontObjectLayer;

@SuppressWarnings("serial")
public class GamePanel extends AbstractGamePanel {

	private GameClient gc;
	public final int window_width = GameClient.window_width,window_height=GameClient.window_height;
	public Mario player1 = new Mario(100, 100, this);
	private FrontObjectLayer obj_layer = new FrontObjectLayer(1, this);
	private BackObjectLayer img_layer = new BackObjectLayer(1, this);
	private BackGroundLayer bg_layer = new BackGroundLayer(BackGroundLayer.BackgroundType.GAME);
	private KeyListener listener;
	
	private enum TriggerState{
		UNTRIGGERED,TRIGGERING,TRIGGERED
	};
	private static TriggerState show_bonus = TriggerState.UNTRIGGERED;
	private JLabel bonus_label;
	private JLabel bonus_text_label;
	private JLabel bonus_bg_label;
	private BlinkingLabel tip_label;
	
	
	
	private boolean should_event = true;
	
	
	public GamePanel(SoundManager sound_mgr) {
		super(sound_mgr);
		
		bonus_bg_label = new JLabel();
		bonus_bg_label.setVisible(false);
		bonus_bg_label.setOpaque(true);
		bonus_bg_label.setBackground(Color.BLACK);
		bonus_bg_label.setBounds(0,0,800,600);
		
		bonus_label = new JLabel();
		bonus_label.setIcon(new ImageIcon(ImageLoader.loadImage(ImageName.BONUS)[0]));
		bonus_label.setBounds(100,50,750,475);
		bonus_bg_label.add(bonus_label);
		
		bonus_text_label = new JLabel();
		bonus_text_label.setText("恭喜您完成了只能完成一次的隐藏关卡！请拿好您的隐藏奖品！");
		bonus_text_label.setForeground(Color.WHITE);
		bonus_text_label.setFont(new Font("微软雅黑", 0, 20));
		bonus_text_label.setHorizontalAlignment(JLabel.CENTER);
		bonus_text_label.setBounds(0,500,800,100);
		bonus_bg_label.add(bonus_text_label);
		
		tip_label = new BlinkingLabel();
		tip_label.setText("<html><body>按任意键继续</body></html>");
		tip_label.setForeground(Color.WHITE);
		tip_label.setFont(new Font("微软雅黑", 1, 16));
		tip_label.setBounds(675, 550, 200, 50);
		bonus_bg_label.add(tip_label);
		
	}
	
	@Override
	public void removeFromGameClient(GameClient gc) {
		gc.removeKeyListener(listener);
	}

	@Override
	public void addToGameClient(GameClient gc) {
		//fix: so we can create dual buffer
		this.gc = gc;
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
		if(show_bonus == TriggerState.TRIGGERING) {
			bonus_bg_label.paint(g);
		}else {
			bg_layer.render(g,player1);
			img_layer.draw(g);
			obj_layer.draw(g);
			player1.draw(g);
			if(should_event) {
				if(player1.live == false && player1.y >= 610) {
					should_event = false;
					tellEvent(new GameOverAndCanShowScreen());
				}else if(player1.finish == true){
					should_event = false;
					tellEvent(new GameWonAndCanShowEpilogue());
				}
			}	
		}
	}

	public FrontObjectLayer getObjectLayer() {
		return obj_layer;
	}
	
	public Image createImage(int width,int height) {
		return gc.createImage(width, height);
	}
	
	public void showBonus() {
		if(show_bonus == TriggerState.UNTRIGGERED) {
			show_bonus = TriggerState.TRIGGERING;
			gc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					show_bonus = TriggerState.TRIGGERED;
					gc.removeKeyListener(this);
				}
			});	
		}
	}

}
