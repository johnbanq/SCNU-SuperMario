package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.*;
import sounds.GameAudio;
import ui.*;
import ui.background.BackGroundLayer;

@SuppressWarnings("serial")
public class GameClient extends JFrame // JFrame重画不调用update 方法 双缓冲代码要写到paint中
{
	public final static int window_width = 800, window_height = 600;
	public boolean d_menu = true;
	public boolean d_game = false;
	protected boolean initialize = false;

	Thread paint_thread;

	public Mario player1 = new Mario(100, 100, this);
	public FrontObjectLayer obj_map = new FrontObjectLayer(1, this);
	public BackObjectLayer img_map = new BackObjectLayer(1, this);
	Menu menu = new Menu(this);
	public JButton b_restart = null;
	public JLabel label_bg = null;
	public JLabel label_gameover = null;
	public JLabel label_win = null;
	public MainMenuPanel mmpanel = new MainMenuPanel(this);
	
	private Image buffer_img;
	GameClient() {
		super();
		
		//setup the game window
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
		if(buffer_img == null)
			buffer_img = this.createImage(window_width, window_height);
		Graphics buf_g= buffer_img.getGraphics();

		if (player1.live == false && player1.y >= 610)// 人物已经死亡且落于屏幕外转换到菜单界面
		{
			d_menu = true;
			d_game = false;
		}
		if (d_menu == true) {
			mmpanel.paint(buf_g);
			menu.draw(buf_g);
			label_bg.setIcon(new ImageIcon(buffer_img));// 将背景图片放入背景Label中
		} else if (d_menu == false) {
		}
		if (d_game == true) {
			//bgmap.render(buf_g,player1);
			img_map.draw(buf_g);
			obj_map.draw(buf_g);
			player1.draw(buf_g);
		} else if (d_game == false) {

		}
		g.drawImage(buffer_img, 0, 0, null);
	}

	public void launchFrame() {
		this.setTitle("Super Mario");
		paint_thread = new Thread(new PaintThread());
		paint_thread.start();
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				player1.keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				player1.keyReleased(e);
			}
		});
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
