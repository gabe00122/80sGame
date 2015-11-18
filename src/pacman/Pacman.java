package pacman;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import gabe00122.swinggames.Input;
import gabe00122.swinggames.Sprite;

public class Pacman extends Actor{
	private Sprite shipSprite;
	private static final double SPEED = 150;
	
	public Pacman(){
		super();
		
		setPosition(100, 100);
		setSize(50, 50);
		
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
		Input input = getGame().getInput();
		
		if(input.isKeyDown(KeyEvent.VK_UP)){
			shipSprite.setRotation(270);
			
			if(canMoveY(-SPEED * delta))
			{
				move(0, -SPEED * delta);
			}
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			shipSprite.setRotation(90);
			if(canMoveY(SPEED * delta))
			{
				move(0, SPEED * delta);
			}
		} 
		if(input.isKeyDown(KeyEvent.VK_LEFT)){
			shipSprite.setRotation(180);
			
			if(canMoveX(-SPEED * delta))
			{
				move(-SPEED * delta, 0);
			}
		} 
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			shipSprite.setRotation(0);

			if(canMoveX(SPEED * delta))
			{
				move(SPEED * delta, 0);
			}
		}
		
		for (Actor other: getGame().checkCollisons(getX(), getY(), getWidth(), getHeight())) {
			collison(other);
		}
	}
	
	private void collison(Actor other){
		if(other instanceof PacDot){
			getGame().removeActor(other);
		}
	}
	
}
