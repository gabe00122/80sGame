package pacman;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import gabe00122.swinggames.Input;
import gabe00122.swinggames.Sprite;

public class Pacman extends Actor{
	private Sprite shipSprite;
	private static final double SPEED = 150;;
	
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
			if(canMoveY(-SPEED * delta))
			{
				move(0, -SPEED * delta);
			}
			shipSprite.setRotation(0);
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			if(canMoveY(SPEED * delta))
			{
				move(0, SPEED * delta);
			}
			shipSprite.setRotation(180);
		} 
		if(input.isKeyDown(KeyEvent.VK_LEFT)){
			if(canMoveX(-SPEED * delta))
			{
				move(-SPEED * delta, 0);
			}
			shipSprite.setRotation(270);
		} 
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			if(canMoveX(SPEED * delta))
			{
				move(SPEED * delta, 0);
			}
			shipSprite.setRotation(90);
		}
	}
	
}
