package rpg.game.tutorial;

public class Bullet {
	
	
	float targetX,targetY,posX,posY;
	
	public Bullet(float targetX,float targetY,float sourceX,float sourceY)
	{
		this.targetX = targetX;
		this.targetY = targetY;
		this.posX = sourceX;
		this.posY = sourceY;
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
