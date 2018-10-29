package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import assets.ImageLoader;
import assets.ImageName;
import main.GameClient;

public class Brick extends GameObject 
{
	public enum VariantType{
		V1,V2
	}
	
	private static Image[] imgs = ImageLoader.loadImage(tk, ImageName.BRICK);
	
	private final int b_w,b_h;
	private final VariantType type;
	
	public Brick(int x, int y, int b_w, int b_h,VariantType type,GameClient gc) 
	{
		super(x, y, gc);
		this.b_w=b_w;
		this.b_h=b_h;
		this.type=type;
		all_w=obj_w*b_w;
		all_h=obj_h*b_h;
		obj_w=30;
		obj_h=30;
	}
	
	public void draw(Graphics g)
	{
		super.draw(g);
		int step=1;
		Image img = null;
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(getPosX(),getPosY(),5,5);
		g.fillOval(getPosX()+obj_w*b_w,getPosY(),5,5);
		g.setColor(c);
		
		for(int w = 1;w<=b_w;w++)
		{
			for(int h = 1; h<=b_h;h++)
			{
				img=imgs[type.ordinal()];
				g.drawImage(img,getPosX()+(w-1)*obj_w,getPosY()+(h-1)*obj_h,null);
			}
		}
	}
	public Rectangle getTotalRectangle()
	{
		return new Rectangle(getPosX(),getPosY(),obj_w*b_w,obj_h*b_h);
	}

}
