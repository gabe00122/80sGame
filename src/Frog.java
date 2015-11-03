import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import gabe00122.swinggames.Input;
import gabe00122.swinggames.Sprite;

public class Frog extends Actor{
	private Sprite shipSprite;
	private double hopCoolDown;
	
	private static final double HOPE_DELAY = .25;
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	public Frog(){
		super();
		hopCoolDown = 0;
		
		setPosition(100, 100);
		setSize(50, 50);
		
		shipSprite = new Sprite(Resources.ship);
		shipSprite.setSize(getWeight(), getHight());
	}
	
	@Override
	public void draw(Graphics2D g) {
		shipSprite.setPosition(getX(), getY());
		shipSprite.draw(g);
	}

	@Override
	public void update(double delta) {
		Input input = getGame().getInput();
		
		if(input.isKeyDown(KeyEvent.VK_UP)){
			hop(Frog.UP);
		}
		else if(input.isKeyDown(KeyEvent.VK_DOWN)){
			hop(Frog.DOWN);
		}
		else if(input.isKeyDown(KeyEvent.VK_LEFT)){
			hop(Frog.LEFT);
		}
		else if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			hop(Frog.RIGHT);
		}
		
		
		if(hopCoolDown > 0){
			hopCoolDown -= delta;
		}
	}
	
	public void hop(int direction){
		if(hopCoolDown <= 0){
			if(direction == UP){
				move(0, -getHight());
				shipSprite.setRotation(0);
			} else if(direction == DOWN){
				move(0, getHight());
				shipSprite.setRotation(180);
			} else if(direction == LEFT){
				move(-getWeight(), 0);
				shipSprite.setRotation(270);
			} else if(direction == RIGHT){
				move(getWeight(), 0);
				shipSprite.setRotation(90);
			}
			hopCoolDown = HOPE_DELAY;
		}
	}
	
}
