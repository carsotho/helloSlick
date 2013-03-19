package nu.tholander.HelloSlick;

import java.util.ArrayList;

import nu.tholander.sprites.EnemyShip;
import nu.tholander.sprites.Spaceship;
import org.newdawn.slick.*;

public class HelloSlick extends BasicGame {

	private float x;
	private float y;
	private Image bg;
	private Spaceship ss;
	private ArrayList<EnemyShip> Enemies = new ArrayList<EnemyShip>();

	public HelloSlick() {
		super("Hello World");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		x = gc.getWidth()/2 - 24;
		y = gc.getHeight()/2 - 24;
		
		bg = new Image("images/Blue-Stars-In-Space.jpg");
		
		ss = new Spaceship("images/spaceship.png", 48, 48, x, y);
		ss.setCenterOfRotation(48/2, 48/2);
		
		Enemies.add(new EnemyShip("images/spaceship.png",48,48, 300, 300));
		//Enemies.add(new EnemyShip("images/spaceship.png",48,48, 300, 350));
		//Enemies.add(new EnemyShip("images/spaceship.png",48,48, 300, 250));
		//Enemies.add(new EnemyShip("images/spaceship.png",48,48, 300, 200));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		for(EnemyShip es : Enemies)
			es.updateAngle(ss.getX(),ss.getY(), delta);
		
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_W)) {
			float rotation = (float) Math.toRadians(ss.getRotation());
			x += Spaceship.velocity * delta* Math.sin(rotation);
			y -= Spaceship.velocity * delta * Math.cos(rotation);
			
			ss.setX(x);
			ss.setY(y);
		}

		if (input.isKeyDown(Input.KEY_A)) {
			ss.rotate(.1f * delta);
		}

		if (input.isKeyDown(Input.KEY_D)) {
			ss.rotate(-.1f * delta);
		}
		
		ss.setFire(input.isKeyDown(Input.KEY_SPACE));
		
		if(ss.getFire()){
			for(EnemyShip es : Enemies){
				if(ss.getLaserLine().intersects(es.getBounds())){
					Enemies.remove(es);
					break;
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		bg.draw();
		ss.render(gc, g);
		for(EnemyShip es : Enemies)
			es.render(gc, g,ss.getX(),ss.getY());
		
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new HelloSlick());

		app.setDisplayMode(800, 600, false);
		app.start();
	}
}