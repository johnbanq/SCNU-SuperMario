package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.*;
import sounds.GameAudio;
import ui.*;
import ui.background.BackGroundMap;
import ui.background.BackGroundMap2;

public class GameClient extends JFrame // JFrame重画不调用update 方法 双缓冲代码要写到paint中
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int window_width = 800, window_height = 600;
	protected Image offscreen = null;
	public boolean d_menu = true;
	public boolean d_game = false;
	protected boolean initialize = false;

	Thread paint_thread = null;
	public Mario player1 = new Mario(100, 100, this);
	BackGroundMap2 bgmap = new BackGroundMap2(BackGroundMap2.BackgroundType.CLOUD_AND_FOREST, this);
	public ObjectMap obj_map = new ObjectMap(1, this);
	public ImageMap img_map = new ImageMap(1, this);
	Menu menu = new Menu(this);
	public JButton b_start = null;
	public JButton b_exit = null;
	public JButton b_restart = null;
	public JLabel label_t1 = null;
	public JLabel label_bg = null;
	public JLabel label_gameover = null;
	public JLabel label_win = null;
	Graphics offscreen_g = null;

	GameClient() {
		super();
		this.setBounds(0, 0, window_width, window_height);
		this.setVisible(true);
		this.setResizable(false);
		this.addWindowListener(new CloseWindowListener());
		this.setLayout(null);
		this.setBackground(Color.cyan);

	}

	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}

	public void paint(Graphics g) {
		// 双缓冲准备
		if (initialize == false) {
			if (offscreen == null) {
				offscreen = this.createImage(window_width, window_height);// 后置缓存
			}
			offscreen_g = offscreen.getGraphics();
			initialize = true;
		}

		if (player1.live == false && player1.y >= 610)// 人物已经死亡且落于屏幕外转换到菜单界面
		{
			d_menu = true;
			d_game = false;
		}
		if (d_menu == true) {
			menu.draw(offscreen_g);
			label_bg.setIcon(new ImageIcon(offscreen));// 将背景图片放入背景Label中
		} else if (d_menu == false) {
			b_start.setVisible(false);
			b_exit.setVisible(false);
			b_restart.setVisible(false);
			label_t1.setVisible(false);
		}
		if (d_game == true) {
			bgmap.draw(offscreen_g);
			img_map.draw(offscreen_g);
			obj_map.draw(offscreen_g);
			player1.draw(offscreen_g);
		} else if (d_game == false) {

		}
		g.drawImage(offscreen, 0, 0, null);
	}

	public void launchFrame() {
		this.setTitle("Super Mario");
		paint_thread = new Thread(new PaintThread());
		paint_thread.start();
		this.addKeyListener(new GameKeyListener());
		this.addMouseListener(new GameMouseListener());
	}

	private class GameKeyListener extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			player1.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			player1.keyReleased(e);
		}
	}

	private class GameMouseListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			new GameAudio("鼠标进入").start();
		}

		public void mouseExited(MouseEvent e) {
			new GameAudio("鼠标进入").start();
		}

	}

	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();// 为什么错
				try {
					Thread.sleep(49);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		GameClient gc = new GameClient();
		gc.launchFrame();
	}
}
