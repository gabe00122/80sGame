package pacman;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class MovingActor extends Actor {
	public static final int NONE = -1, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	private double speed;
	private int direction;
	private int preferredDirection;
	
	private boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;
	
	public MovingActor() {
		direction = NONE;
		preferredDirection = NONE;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public void setTargetDirection(int d){
		preferredDirection = d;
	}
	
	public void setDirection(int d){
		direction = NONE;
	}
	
	public int getDirection(){
		return direction;
	}
	
	@Override
	public void update(double delta) {
		boolean canMoveUp = canMoveY(-speed * delta);
		boolean canMoveDown = canMoveY(speed * delta);
		boolean canMoveLeft = canMoveX(-speed * delta);
		boolean canMoveRight = canMoveX(speed * delta);
		
		if(this.canMoveUp != canMoveUp ||
				this.canMoveDown != canMoveDown ||
				this.canMoveLeft != canMoveLeft ||
				this.canMoveRight != canMoveRight){
			this.canMoveUp = canMoveUp;
			this.canMoveDown = canMoveDown;
			this.canMoveLeft = canMoveLeft;
			this.canMoveRight = canMoveRight;
			onNewDirection();
		}
		
		if(preferredDirection == UP && canMoveUp)
		{
			direction = preferredDirection;
		}
		else if(preferredDirection == DOWN && canMoveDown)
		{
			direction = preferredDirection;
		}
		else if(preferredDirection == LEFT && canMoveLeft)
		{
			direction = preferredDirection;
		}
		else if(preferredDirection == RIGHT && canMoveRight)
		{
			direction = preferredDirection;
		}
		
		switch (direction) {
		case UP:
			move(0, -speed * delta);
			break;
		case DOWN:
			move(0, speed * delta);
			break;
		case LEFT:
			move(-speed * delta, 0);
			break;
		case RIGHT:
			move(speed * delta, 0);
			break;
		}
	}
	
	public void move(double changeX, double changeY){
		if(!canMoveX(changeX)){
			changeX = 0;
		}
		
		while(!canMoveY(changeY)){
			changeY = 0;
		}
		super.move(changeX, changeY);
	}
	
	public boolean canMoveX(double changeX){
		double newX = changeX + getX();
		
		double pad = Math.abs(changeX);
		
		if(changeX > 0){
			return !collidesWithTile(newX+getWidth()/2, getY()) &&
				   !collidesWithTile(newX+getWidth()/2, getY() + getHeight()/2 -pad) &&
				   !collidesWithTile(newX+getWidth()/2, getY() - getHeight()/2 +pad);
		} else if(changeX < 0){
			return !collidesWithTile(newX-getWidth()/2, getY()) &&
				   !collidesWithTile(newX-getWidth()/2, getY() + getHeight()/2 -pad) &&
				   !collidesWithTile(newX-getWidth()/2, getY() - getHeight()/2 +pad);
		}
		
		return true;
	}
	
	public boolean canMoveY(double changeY){
		double newY = changeY + getY();
		
		double pad = Math.abs(changeY);
		
		if(changeY > 0){
			return !collidesWithTile(getX(), newY + getHeight()/2) &&
				   !collidesWithTile(getX()+getWidth()/2 -pad, newY + getHeight()/2) &&
				   !collidesWithTile(getX()-getWidth()/2 +pad, newY + getHeight()/2);
		} else if(changeY < 0){
			return !collidesWithTile(getX(), newY - getHeight()/2) &&
				   !collidesWithTile(getX()+getWidth()/2 -pad, newY - getHeight()/2) &&
				   !collidesWithTile(getX()-getWidth()/2 +pad, newY - getHeight()/2);
		}
		
		return true;
	}
	
	public void onNewDirection(){
		
	}
	
	public List<Integer> getDirectionChoses(){
		List<Integer> out = new ArrayList<>();
		
		if(canMoveUp){
			out.add(UP);
		}
		if(canMoveDown){
			out.add(DOWN);
		}
		if(canMoveLeft){
			out.add(LEFT);
		}
		if(canMoveRight){
			out.add(RIGHT);
		}
		
		return out;
	}
	
	private boolean collidesWithTile(double x, double y){
		return collidesWithTile(getGame().getMaze().getTileAt(x, y));
	}
	
	public boolean collidesWithTile(MazeTile t){
		return false;
	}
}
