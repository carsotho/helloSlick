package nu.tholander.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;

public class Spaceship extends SpriteSheet {
	
	private float x;
	private float y;
	private Boolean fire = false;
	private Line laserLine;
	public static final float velocity = .2f;
	
	public Spaceship(String imageUrl, int tw, int th, float x, float y) throws SlickException{
		super(imageUrl, tw, th);
		this.x = x;
		this.y = y;
		this.laserLine = new Line(x, y);
	}

	public float getX() {
		return x+this.getWidth()/2;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y+this.getHeight()/2;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	public Boolean getFire() {
		return fire;
	}

	public void setFire(Boolean fire) {
		this.fire = fire;
	}
	
	public Line getLaserLine() {
		return laserLine;
	}

	public void render(GameContainer gc, Graphics g){
		this.draw(x, y);
		
		if(fire){
			//System.out.println("fire");
			this.fireLaser(g);
		}
		else
			laserLine.set(0, 0, 0, 0);
	}

	private void fireLaser(Graphics g){
		float rotation = (float) Math.toRadians(this.getRotation());

		float laser = 5f;
		
		float x0 = x+this.getWidth()/2;
		
		float y0 = y +this.getHeight()/2;
		float x1 = (((float)Math.sin(rotation)*this.getWidth()/2) + x0);
		float y1 =  (((float)Math.cos(rotation)*-this.getHeight()/2) + y0);
		float x2 =  ((laser*(float)Math.sin(rotation)*this.getWidth()/2) + x0);
		float y2 = ((laser*(float)Math.cos(rotation)*-this.getHeight()/2) + y0);
		
		laserLine.set(x1, y1, x2, y2);
		g.drawGradientLine(x1, y1, Color.pink, x2, y2, Color.red);
		g.drawGradientLine(x1-1, y1, Color.pink, x2, y2, Color.red);
		g.drawGradientLine(x1-2, y1, Color.pink, x2, y2, Color.red);
	}
	
	

}
