package pacman;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import swinggames.Input;
import swinggames.Sprite;

public class Pacman extends MovingActor{
	private Sprite shipSprite;
	private static final double SPEED = 150;
	
	public Pacman(){
		super();
		
		setPosition(100, 100);
		setSize(50, 50);
		
		setSpeed(SPEED);
		
		shipSprite = new Sprite(Resources.ship);
		shipSprite.setSize(getWidth(), getHeight());
	}
	
	@Override
	public void draw(Graphics2D g) {
		shipSprite.setPosition(getX(), getY());
		shipSprite.draw(g);
	}

	@Override
	public boolean collidesWithTile(MazeTile t) {
		return t.playerCollides();
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		
		Input input = getGame().getInput();
		
		if(input.isKeyDown(KeyEvent.VK_UP)){
			shipSprite.setRotation(270);
			
			setDirection(UP);
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			shipSprite.setRotation(90);
			
			setDirection(DOWN);
		} 
		if(input.isKeyDown(KeyEvent.VK_LEFT)){
			shipSprite.setRotation(180);
			
			setDirection(LEFT);
		} 
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			shipSprite.setRotation(0);

			setDirection(RIGHT);
		}
		
		for (Actor other: getGame().checkCollisons(getX(), getY(), getWidth(), getHeight())) {
			collison(other);
		}
	}
	
	private void collison(Actor other){
		if(other instanceof PacDot){
			getGame().removeActor(other);
			getGame().removePacDot();
		}
	}
	
}
