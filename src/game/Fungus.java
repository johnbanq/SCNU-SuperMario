package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import assets.ImageLoader;
import assets.ImageName;
import assets.MusicName;
import ui.*;
import main.GameClient;


public class Fungus extends GameCreature 
{

	private static Image[] frames = ImageLoader.loadImage(tk, ImageName.FUNGUS);
	
	protected boolean hit=false;
	protected int XSPE=2,YSPE=15,yadd=3,xspe=0,ysep=0;
	private int d_time=1;
	private boolean initialize = false;
	
	public Fungus(int x, int y,MoveDirection move_dir,GamePanel panel) {
		super(x, y, panel);
		if(move_dir==MoveDirection.LEFT) xspe=-XSPE;
		else xspe=XSPE;
		obj_w=35;
		obj_h=35;
		all_w=35;
		all_h=35;
	}

	public void draw(Graphics g) {
		super.draw(g);
		
		if(initialize==false)
		{
			for(int i=0;i<frames.length;i++)
			{
				g.drawImage(frames[i],-200,-200,null);
			}
			initialize=true;
		}
		Image img = null;
		if(hit==false)
		{
			if(d_time<=3)
			{
				img=frames[0];
				d_time++;
			}
			else if(d_time>3&&d_time<=6)
			{
				img=frames[1];
				if(d_time==6)
				d_time=1;
				else 
				d_time++;
			}
		}
		else if(hit==true)
		{
			all_h=20;
			img=frames[2];
		}
		g.drawImage(img, getPosX(), getPosY(), null);
		
		Color c = g.getColor();
		g.drawImage(img, getPos().x, getPos().y, null);
		g.setColor(Color.black);
		g.fillOval(getPosX(),getPosY(),5,5);
		g.fillOval(getPosX()+all_w,getPosY(),5,5);
		g.fillOval(getPosX(),getPosY()+all_h,5,5);
		g.setColor(c);
		
		touchWithObjs();
	}

	public void move() {
		super.move();
		if(draw==false) return;
		xMove();
		yMove();
	}

	protected void xMove() {
		if(hit==true) return;
		setPosX(getPosX() + xspe);
	}

	protected void yMove() {
		if(act==Action.UNSTAND)
		{
			yspe+=yadd;
		}
		else if (act==Action.STAND)
		{
			yspe=1;
		}
		setPosY(getPosY() + yspe);
	}

	public void touchWithHero(Hero hero) {
		super.touchWithHero(hero);
		if(draw==false||hero.live==false) return;
		if(hero.getNextRectangle().intersects(getNextRectangle())
			||hero.getARectangle(hero.x+1, hero.y,hero.hero_w,hero.hero_h).intersects(getNextRectangle())
			||hero.getARectangle(hero.x-1,hero.y,hero.hero_w,hero.hero_h).intersects(getNextRectangle())
				)
		{
			if(hero.y+hero.hero_h<=getPosY())
			{
				touchhero=Action.BUNT;
			}
			else if(hero.x<=getPosX())
			{
				touchhero=Action.LTOUCH;
			}
			else if(hero.x+hero.hero_w>=getPosX()+all_w)
			{
				touchhero=Action.RTOUCH;
			}
		}
		else
		{
			touchhero=Action.UNTOUCH;
		}
		action(panel.player1);
	}
	
	protected void action(Hero hero) {
		super.doAction();
		if(touchhero==Action.BUNT)
		{
			hero.speed_y=-hero.y_add;
			if(hit==true)
			{
				disappear();
			}
			hit=true;
			
			//打击音效
			panel.getSoundManager().playSound(MusicName.打击);
		}
		else if(touchhero==Action.LTOUCH)
		{
			hero.die();
		}
		else if(touchhero==Action.RTOUCH)
		{
			hero.die();
		}
	}
	protected void touchWithObjs() 
	{
		if(draw==false||available==false) return;
		GameObject obj1=null;
		GameObject obj2=null;
		for(int i=0;i<objs.size();i++)
		{
			GameObject obj=null;
			obj = objs.get(i);
			if((obj.draw==true&&getNextRectangle().intersects(obj.getTotalRectangle())==true
					&&(obj!=obj1&&obj!=obj2&&obj!=this))||obj.throughCheck(this)
					||((getPosX()+xspe>obj.getPosX()&&getPosX()+xspe<obj.getPosX()+obj.all_w&&getPosY()>obj.getPosY()&&getPosY()<obj.getPosY()+obj.all_h)||(getPosX()+all_w+xspe>obj.getPosX()&&getPosX()+all_w+xspe<obj.getPosX()+obj.all_w&&getPosY()>obj.getPosY()&&getPosY()<obj.getPosY()+obj.all_h)))
			{
				if((getPosX()+xspe>obj.getPosX()&&getPosX()+xspe<obj.getPosX()+obj.all_w&&getPosY()>obj.getPosY()&&getPosY()<obj.getPosY()+obj.all_h)||(getPosX()+all_w+xspe>obj.getPosX()&&getPosX()+all_w+xspe<obj.getPosX()+obj.all_w&&getPosY()>obj.getPosY()&&getPosY()<obj.getPosY()+obj.all_h))
				{//穿越护体检测2
					if(obj.getPosY()>=getPosY()) return;
					if(xspe>=0)
					{
						setPosX(obj.getPosX()-all_w);
					}
					else
					{
						setPosX(obj.getPosX()+obj.all_w);
					}
				//	System.out.println("FU穿越物体检测1");
				}
				if(obj.throughCheck(this))//穿越物体检测2
				{
					if(obj.getPosY()>=getPosY()) return;
					setPosX(getPosX() - xspe);
					setPosY(getPosY() - yspe);
				//	System.out.println("FU穿越物体检测2");
				}
				if(obj1==null)
				{
					obj1=obj;
					//System.out.println("FU obj1  "+obj.getRectangle());
				}
				else if(obj1!=null)
				{
					obj2=obj;
					//System.out.println("FU obj2  "+obj.getRectangle());
				}
				if(obj1!=null&&obj2!=null)
				{
					//System.out.println("FU 这里需要一个新的obj了   "+obj.getRectangle()+" obj1 "+obj1.getRectangle()+" obj2 "+obj2.getRectangle());
				}
			}
		}
		
		if(obj1!=null&&obj2==null)//只有一个物体与mario接触时
		{
			if(getPosY()<=obj1.getPosY())
			{
				setPosY(obj1.getPosY()-all_h);
				act=Action.STAND;
			}
			else
			{
				act=Action.UNSTAND;
			}
			
			if(getPosX()>=obj1.getPosX()+obj1.all_w&&xspe<0)
			{
				touch=Action.LTOUCH;
				setPosX(obj1.getPosX()+obj1.all_w+XSPE);	
				xspe=XSPE;
			}
			else if(getPosX()+all_w<=obj1.getPosX()&&xspe>0)
			{
				touch=Action.RTOUCH;
				setPosX(obj1.getPosX()-all_w-XSPE);
				xspe=-XSPE;
			}
			else if(getPosY()>=obj1.getPosY()+obj1.all_h&&yspe<0)
			{
				touch=Action.BUNT;
			}
			else
			{
				touch=Action.UNTOUCH;
			}
			//if(touch!=Action.UNTOUCH)
			//System.out.println("FU有一个物体即将碰撞 "+act+" "+touch+" x "+x+" xspe "+xspe+" y "+y);
		}
		else if(obj1!=null&&obj2!=null)//有两个物体与mario接触时
		{
			int ground=0;//找出作为地面的物体和作为墙的物体

			if(getPosX()+all_w>obj1.getPosX()&&getPosX()<obj1.getPosX()+obj1.all_w&&obj1.getPosY()>getPosY())
			{
				ground=1;
			}
			else if(getPosX()+all_w>obj2.getPosX()&&getPosX()<=obj2.getPosX()+obj2.all_w&&obj2.getPosY()>getPosY())
			{
				ground=2;
			}
			else
			{
				System.out.println("错误！无物体在mario下");
				return ;
			}
			//对于墙物体的处理
			GameObject obj_wall=null,obj_ground=null;
			if(ground==1)
			{
				obj_wall=obj2;
				obj_ground=obj1;
			}
			else if(ground==2)
			{
				obj_wall=obj1;
				obj_ground=obj2;
			}
			
			//两个物体碰撞时垂直方向分析
			if(getPosX()<obj_ground.getPosX()+obj_ground.all_w||getPosX()+all_w>obj_ground.getPosX()) //站在地面块中
			{
				act=Action.STAND;
				setPosY(obj_ground.getPosY()-all_h);
			}
			else
			{
				act=Action.UNSTAND;
			}
			
			//两物体碰撞时水平方向分析
			if(xspe<0)
			{
				touch=Action.LTOUCH;
				setPosX(obj_wall.getPosX()+obj_wall.all_w+XSPE);	
				xspe=XSPE;
			}
			else if(xspe>0)
			{
				touch=Action.RTOUCH;
				setPosX(obj_wall.getPosX()-all_w-XSPE);
				xspe=-XSPE;
			}
			else
			{
				touch=Action.UNTOUCH;
			}
			
			//if(touch!=Action.UNTOUCH)
			System.out.println("FU有两个个物体即将碰撞     "+act+" "+touch+" "+touchhero+" xspe "+xspe+" yspe "+yspe);
		 
		}
		else if(obj1==null&obj2==null)
		{
			act=Action.UNSTAND;
			touch=Action.UNTOUCH;
			//System.out.println("FU没有物体即将碰撞 "+act+" "+touch+" x "+x+" xspe "+xspe+" y "+y+" yspe "+yspe);
		}
	}

}
