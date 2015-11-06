package asteroids;

import java.awt.Graphics2D;

import gabe00122.swinggames.Sprite;

public class Asteroid extends Actor{
	private Sprite sprite;
	
	@Override
	public void init() {
		sprite = new Sprite(Resources.asteroid);
		
		setSpeedX(getGame().getRandom().nextInt(100)-50);
		setSpeedY(getGame().getRandom().nextInt(100)-50);
		
		setAngSpeed(getGame().getRandom().nextInt(50)-25);
	}
	
	@Override
	public void draw(Graphics2D g) {
		sprite.setPosition(getX(), getY());
		sprite.setRotation(getAng());
		sprite.draw(g);
	}
}
