package asteroids;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import swinggames.Input;
import swinggames.Sprite;

public class Ship extends Actor{
	private Sprite shipSprite;
	private boolean fireRelease;
	
	public Ship() {
		setPosition(100, 100);
		setSize(50, 50);
	}
	
	@Override
	public void draw(Graphics2D g) {
		shipSprite.setPosition(getX(), getY());
		shipSprite.setRotation(getAng() + 90);
		shipSprite.draw(g);
	}
	
	
	@Override
	public void update(double delta) {
		super.update(delta);
		Input input = getGame().getInput();
		
		if(input.isKeyDown(KeyEvent.VK_UP)){
			excelerate(getAng(), 100*delta);
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			excelerate(getAng(), -50*delta);
		} 
		if(input.isKeyDown(KeyEvent.VK_LEFT)){
			setAng(getAng() - 10);
		} 
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			setAng(getAng() + 10);
		}
		if(input.isKeyDown(KeyEvent.VK_SPACE)){
			if(fireRelease){
				Shot shot = new Shot();
				shot.setAng(getAng());
				shot.setPosition(getX(), getY());
				shot.setSpeedX(getSpeedX());
				shot.setSpeedY(getSpeedY());
				
				getGame().addActor(shot);
				fireRelease = false;
			}
		} else {
			fireRelease = true;
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		getGame().gameOver();
	}
	
	@Override
	public void init() {
		fireRelease = true;
		
		shipSprite = new Sprite(Resources.ship);
		shipSprite.setSize(getWidth(), getHeight());
	}
	
}
