package model;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Weapon {
	
	

	float targetX,targetY,posX,posY,angle;
	Moving ch;
	Image img;
	
	public Weapon(float targetX,float targetY,float sourceX,float sourceY,float angle,Moving ch,int type) throws SlickException
	{
		if(type == 0)
		{
			img = new Image("res/knife.png");
		}
		else if(type == 1)
		{
			img = new Image("res/bullet.png");
		}
		
		
		this.targetX = targetX;
		this.targetY = targetY;
		this.posX = sourceX;
		this.posY = sourceY;
		this.angle = angle;
		this.ch = ch;
	}
	
	public float getangle()
	{
		return this.angle;
	}
	public float getX()
	{
		return this.posX;
	}
	public float getY()
	{
		return this.posY;	
	}
	public float targetgetX()
	{
		return this.targetX;
	}
	public float targetgetY()
	{
		return this.targetY;	
	}
	public void setX(float x)
	{
		this.posX = x;
	}
	public void setY(float y)
	{
		this.posY = y;
	}
	public Moving getTarget()
	{
		return this.ch;
	}
	
	public Image getImg()
	{
		return this.img;
	}
	
	public void draw(Graphics g)
	{
		
	}
	

}
