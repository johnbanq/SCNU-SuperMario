package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import assets.ImageLoader;
import assets.ImageName;
import main.GameClient;
import sounds.SoundManager;
import ui.components.BlinkingLabel;
import ui.events.GameStartEvent;

@SuppressWarnings("serial")
public class IntroPanel extends AbstractGamePanel {

	public IntroPanel(SoundManager sound_mgr) {
		super(sound_mgr);
	}

	private JLabel bg_label,icon_label;
	private JLabel intro_label;
	private BlinkingLabel guide_label;
	private KeyListener listener;

	private final String[] intro_text = { "Zero娘是Zero动漫社的宝贝", "但在校庆当天，意外发生了——", "Zero娘被天空中的娃娃机抓手抓到了一课里，", "赶快去一课救她吧！" };
	private final String guide_text = "<html><body>按任意键开始游戏</body></html>";

	// render info
	private int tick_to_skip = 0;
	private int shown_row_count = 0;
	private StringBuffer shown_buf = new StringBuffer("<html><body>");

	@Override
	public void removeFromGameClient(GameClient gc) {
		gc.removeKeyListener(listener);
		gc.remove(bg_label);
		bg_label.setVisible(false);
	}

	@Override
	public void addToGameClient(GameClient gc) {
		bg_label = new JLabel();
		bg_label.setBounds(-5, -26, 800, 600);
		bg_label.setVisible(true);
		bg_label.setOpaque(true);
		bg_label.setBackground(Color.BLACK);
		gc.getContentPane().add(bg_label);

		intro_label = new JLabel();
		intro_label.setForeground(Color.WHITE);
		intro_label.setFont(new Font("微软雅黑", 1, 21));
		intro_label.setBounds(20, 0, 500, 200);
		intro_label.setOpaque(false);
		bg_label.add(intro_label);

		guide_label = new BlinkingLabel();
		guide_label.setText(guide_text);
		guide_label.setForeground(Color.WHITE);
		guide_label.setFont(new Font("微软雅黑", 1, 16));
		guide_label.setBounds(600, 500, 200, 50);
		guide_label.setOpaque(false);
		guide_label.setVisible(false);
		bg_label.add(guide_label);
		
		icon_label = new JLabel();
		icon_label.setBounds(225,40, 600, 600);
		icon_label.setOpaque(false);
		icon_label.setIcon(new ImageIcon(ImageLoader.loadImage(ImageName.ICON)[0]));
		bg_label.add(icon_label);

		// the press any key listener
		if (listener == null) {
			listener = new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tellEvent(new GameStartEvent());
				}
			};
		}
		bg_label.addKeyListener(listener);
		bg_label.requestFocus();
	}

	public synchronized void paint(Graphics g) {

		// make the intro type
		tick_intro_text();
		
		bg_label.paint(g);
	}

	private void tick_intro_text() {
		// HACK:to make scrolling effect and lighting effect
		// frame rate assumed to be 50Hz according to framerate in PaintThread
		
		// make the intro type
		if (tick_to_skip != 0) {
			tick_to_skip--;
			return;
		}

		if (shown_row_count < 0) {
			// Noop: means finished display
		} else if (shown_row_count < intro_text.length) {
			shown_buf.append(intro_text[shown_row_count++]);
			shown_buf.append("<br>");
			// display delay
			tick_to_skip = 20;
		} else {// shown_row_count == intro_text.length fully displayed
			guide_label.setVisible(true);
			shown_row_count = -1;
		}
		intro_label.setText(shown_buf.toString() + "</html></body>");
	}

}
