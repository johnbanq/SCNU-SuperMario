package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

import game.Action;
import game.Hero;
import ui.*;
import main.GameClient;

public class GameObject 
{
	public GamePanel panel;
	
	private Point pos;
	public int in_x;
	public int obj_w=28, obj_h=28;//object width and height
	public int all_w,all_h;
	public int hasrun_x=0;
	
	public boolean draw = true,available=true;
	
	protected static Toolkit tk =Toolkit.getDefaultToolkit();
	protected static Random random = new Random();
	protected int ran_num = random.nextInt(10);
	protected Action act=Action.UNSTAND,touch=Action.UNTOUCH,touchhero = Action.UNTOUCH;
	protected Hero player=null;
	
	
	
	GameObject(int x,int y,GamePanel panel)
	{
		this.pos = new Point(x,y);
		this.panel=panel;
		all_w=obj_w;
		all_h=obj_h;
		in_x=x;
	}
	
	public void draw(Graphics g)
	{
		setAvailable();
		getHasrun();
		touchWithHero(panel.player1);
		move();
	}
	
	protected void getHasrun() {
		this.hasrun_x = panel.player1.hasrun_x;
	}

	protected void setAvailable() 
	{
		if(available==false) return;
		if(getTotalRectangle().intersects(new Rectangle(0,0,GameClient.window_width,GameClient.window_height))==false)
		{
			if(pos.x<=-GameClient.window_width/2)
			{
				draw=false;
				//available=false;
			}
		}
	}

	public void move()
	{
		if(available==true)
			player = panel.player1;
		{
			if(player.x+player.speed_x>Hero.LIM_X2&&player.finish==false)
			{
				pos.x-=player.speed_x;
			}
		}
		
	}
	
	public Rectangle getTotalRectangle(){
		return new Rectangle(pos.x,pos.y,all_w,all_h);
	}
	
	protected void touchWithHero(Hero hero){
		if(this.draw==false) 
			return ;
	}
	
	//collision checker
	public boolean throughCheck(Hero hero)
	{
		int x,y,x1,y1,r_x,r_y,r_w,r_h;
		x=hero.x;
		y=hero.y;
		x1=hero.x+hero.speed_x;
		y1=hero.y+hero.speed_y;
		if(x<=x1)
		{
			r_x=x;
		}
		else r_x=x1;
		if(y<=y1)
		{
			r_y=y;
		}
		else r_y=y1;
		if(hero.speed_x<0) r_w=-hero.speed_x;
		else r_w=hero.speed_x;
		if(hero.speed_y<0) r_h=-hero.speed_y;
		else r_h=hero.speed_y;
		Rectangle move_r=new Rectangle(r_x,r_y,r_w,r_h);
		if(move_r.intersects(this.getTotalRectangle()))
		{
			return true;
		}
		else return false;
	}
	
	public boolean throughCheck(GameCreature gc)
	{
		int x,y,x1,y1,r_x,r_y,r_w,r_h;
		x=gc.getPosX();
		y=gc.getPosY();
		x1=gc.getPosX()+gc.xspe;
		y1=gc.getPosY()+gc.yspe;
		if(x<=x1)
		{
			r_x=x;
		}
		else r_x=x1;
		if(y<=y1)
		{
			r_y=y;
		}
		else r_y=y1;
		if(gc.xspe<0) r_w=-gc.xspe;
		else r_w=gc.xspe;
		if(gc.yspe<0) r_h=-gc.yspe;
		else r_h=gc.yspe;
		Rectangle move_r=new Rectangle(r_x,r_y,r_w,r_h);
		if(move_r.intersects(this.getTotalRectangle()))
		{
			return true;
		}
		else return false;
	}
	
	protected void doAction(){
		
	}

	public Point getPos() {
		return new Point(pos.x,pos.y);
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}
	
	public int getPosX() {
		return pos.x;
	}
	
	public int getPosY() {
		return pos.y;
	}
	
	public void setPosX(int v) {
		pos.x=v;
	}
	
	public void setPosY(int v) {
		pos.y=v;
	}
}
