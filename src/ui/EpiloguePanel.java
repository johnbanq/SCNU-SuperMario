package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import assets.ImageLoader;
import assets.ImageName;
import main.GameClient;
import sounds.SoundManager;
import ui.components.AlphaLabel;
import ui.components.BlinkingLabel;
import ui.events.GameStartEvent;
import ui.events.GameWonAndCanShowScreen;

@SuppressWarnings("serial")
public class EpiloguePanel extends AbstractGamePanel {

	public EpiloguePanel(GamePanel gp,SoundManager sound_mgr) {
		super(sound_mgr);
		this.gp = gp;
		System.out.println("NEW!");
	}

	private JLabel bg_label;
	private JLabel chat_layout_label;//in charge of layout
	private AlphaLabel chat_bg_label;//in charge of color
	
	private JLabel head_icon_label,name_label;
	private JLabel chat_label;
	private BlinkingLabel guide_label;
	private KeyListener listener;
	
	private GamePanel gp;
	
	private final String name = "[Zero娘]";
	private final String[] chat_text = { "谢谢你，华里奥!", "送你一张华师的明信片!", "告诉你一个秘密，华师的正门好像隐藏着什么哦" };
	private final String guide_text = "<html><body>按任意键继续</body></html>";
	
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
		bg_label.setOpaque(false);
		gc.getContentPane().add(bg_label);
		
		head_icon_label = new JLabel();
		head_icon_label.setBounds(0,300,400,400);
		head_icon_label.setIcon(new ImageIcon(ImageLoader.loadImage(ImageName.CHARACTER_PHOTO)[0]));
		head_icon_label.setOpaque(false);
		bg_label.add(head_icon_label);
		
		chat_layout_label = new JLabel();
		chat_layout_label.setBounds(100, 425, 650, 150);
		chat_layout_label.setOpaque(false);
		bg_label.add(chat_layout_label);
		
		name_label = new JLabel();
		name_label.setForeground(Color.WHITE);
		name_label.setFont(new Font("微软雅黑", 1, 21));
		name_label.setText(name);
		name_label.setBounds(60, 5,120,20);
		name_label.setOpaque(false);
		chat_layout_label.add(name_label);

		chat_label = new JLabel();
		chat_label.setForeground(Color.WHITE);
		chat_label.setFont(new Font("微软雅黑", 0, 20));
		chat_label.setBounds(80, 16, 460,120);
		chat_label.setOpaque(false);
		chat_layout_label.add(chat_label);

		guide_label = new BlinkingLabel();
		guide_label.setText(guide_text);
		guide_label.setForeground(Color.WHITE);
		guide_label.setFont(new Font("微软雅黑", 1, 16));
		guide_label.setBounds(540, 100, 200, 50);
		guide_label.setOpaque(false);
		guide_label.setVisible(false);
		chat_layout_label.add(guide_label);
		
		chat_bg_label = new AlphaLabel();
		chat_bg_label.setAlpha(3);
		chat_bg_label.setBounds(0, 0, 650, 150);
		chat_bg_label.setOpaque(true);
		chat_bg_label.setBackground(Color.BLUE);
		chat_layout_label.add(chat_bg_label);

		// the press any key listener
		if (listener == null) {
			listener = new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					tellEvent(new GameWonAndCanShowScreen());
				}
			};
		}
		bg_label.addKeyListener(listener);
		bg_label.requestFocus();
	}

	public synchronized void paint(Graphics g) {
		gp.paint(g);
		tick_epilogue_text();
		bg_label.paint(g);
	}

	private void tick_epilogue_text() {
		// HACK:to make scrolling effect and lighting effect
				// frame rate assumed to be 50Hz according to framerate in PaintThread
				
				// make the intro type
				if (tick_to_skip != 0) {
					tick_to_skip--;
					return;
				}

				if (shown_row_count < 0) {
					// Noop: means finished display
					return;
				} else if (shown_row_count < chat_text.length) {
					shown_buf.append(chat_text[shown_row_count++]);
					shown_buf.append("<br>");
					// display delay
					tick_to_skip = 20;
				} else {// shown_row_count == intro_text.length fully displayed
					guide_label.setVisible(true);
					shown_row_count = -1;
				}
				chat_label.setText(shown_buf.toString() + "</html></body>");
	}

}
