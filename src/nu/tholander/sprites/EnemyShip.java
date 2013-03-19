package nu.tholander.sprites;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class EnemyShip extends SpriteSheet {
	
	private float x;
	private float y;
	private Rectangle bounds;
	
	public EnemyShip(String imageUrl, int tw, int th, float x, float y) throws SlickException{
		super(imageUrl, tw, th, Color.black);
		this.setBounds(new Rectangle(x, y, tw, th));
		this.x = x;
		this.y = y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
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
	
	public void render(GameContainer gc, Graphics g, float playerX, float playerY){
		this.draw(x, y);
		
		g.drawLine(this.getX(), this.getY(), playerX, playerY);
		g.draw(this.bounds);
	}

	public void updateAngle(float x2, float y2, int delta) {
		Vector2f v = new Vector2f(x-x2, y-y2);
		float angle =(float) Math.atan2(v.getY(), v.getX());
		System.out.println(angle);
		this.x += .1f * Math.sin(Math.toRadians(v.getTheta())) * delta;
		this.y -= .1f * Math.cos(Math.toRadians(v.getTheta())) * delta;
		
		this.bounds.setX(this.x);
		this.bounds.setY(this.y);
		
		this.angle =(float) Math.cos(Math.toRadians(v.getTheta()));
	}

}
