package model;

public class Bullet {
	
	
	float targetX,targetY,posX,posY,angle;
	
	public Bullet(float targetX,float targetY,float sourceX,float sourceY,float angle)
	{
		this.targetX = targetX;
		this.targetY = targetY;
		this.posX = sourceX;
		this.posY = sourceY;
		this.angle = angle;
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
	
	

}
