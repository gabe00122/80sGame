package pacman;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import swinggames.Input;
import swinggames.Sprite;

public class Pacman extends MovingActor{
	private Sprite pacmanSprite;
	private static final double SPEED = 150;
	
	public Pacman(){
		super();
		
		setPosition(100, 100);
		setSize(50, 50);
		
		setSpeed(SPEED);
		
		pacmanSprite = new Sprite(Resources.pacman);
		pacmanSprite.setSize(getWidth(), getHeight());
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(getDirection() == UP){
			pacmanSprite.setRotation(270);
		}
		
		if(getDirection() == DOWN){
			pacmanSprite.setRotation(90);
		}
		
		if(getDirection() == LEFT){
			pacmanSprite.setRotation(180);
		}
		
		if(getDirection() == RIGHT){
			pacmanSprite.setRotation(0);
		}
		
		pacmanSprite.setPosition(getX(), getY());
		pacmanSprite.draw(g);
		
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
			setDirection(UP);
		}
		if(input.isKeyDown(KeyEvent.VK_DOWN)){
			setDirection(DOWN);
		} 
		if(input.isKeyDown(KeyEvent.VK_LEFT)){
			setDirection(LEFT);
		} 
		if(input.isKeyDown(KeyEvent.VK_RIGHT)){
			setDirection(RIGHT);
		}
		
		for (Actor other: getGame().checkCollisons(getX(), getY(), getWidth(), getHeight())) {
			collison(other);
		}
	}
	
	private void collison(Actor other){
		if(other instanceof PacDot){
			((PacDot)other).eat();
		}
	}

	@Override
	public void init() {
		
	}
	
}
