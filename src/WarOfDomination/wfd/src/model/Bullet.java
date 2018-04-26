package model;
/**
 * @author Ayberk
 *
 */
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends Weapon{
	
	
	float targetX,targetY,posX,posY,angle;
	Moving ch;
	
	public Bullet(float targetX,float targetY,float sourceX,float sourceY,float angle,Moving ch)  throws SlickException
	{
		super(targetX,targetY,sourceX,sourceY,angle,ch,1);
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
	
	public Image getBullet()
	{
		return super.img;
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(super.img, this.posX, this.posY);
	}
	
	

}
