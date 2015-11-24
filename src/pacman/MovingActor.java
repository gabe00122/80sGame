package pacman;

import java.util.ArrayList;
import java.util.List;

public abstract class MovingActor extends Actor {
	public static final int NONE = -1, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private static final double COLLISION_PADDING = 1.5;
	
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
	
	public void setDirection(int d){
		preferredDirection = d;
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
	}
	
	public void move(double changeX, double changeY){
		while(!canMoveX(changeX)){
			changeX /= 2;
			if(Math.abs(changeX) <= COLLISION_PADDING/4){
				changeX = 0;
				break;
			}
		}
		
		while(!canMoveY(changeY)){
			changeY /= 2;
			if(Math.abs(changeY) <= COLLISION_PADDING/4){
				changeY = 0;
				break;
			}
		}
		
		super.move(changeX, changeY);
	}
	
	public boolean canMoveX(double changeX){
		double newX = changeX + getX();
		
		if(changeX > 0){
			return !collidesWithTile(newX+getWidth()/2, getY()) &&
				   !collidesWithTile(newX+getWidth()/2, getY() + getHeight()/2 -COLLISION_PADDING) &&
				   !collidesWithTile(newX+getWidth()/2, getY() - getHeight()/2 +COLLISION_PADDING);
		} else if(changeX < 0){
			return !collidesWithTile(newX-getWidth()/2, getY()) &&
				   !collidesWithTile(newX-getWidth()/2, getY() + getHeight()/2 -COLLISION_PADDING) &&
				   !collidesWithTile(newX-getWidth()/2, getY() - getHeight()/2 +COLLISION_PADDING);
		}
		
		return true;
	}
	
	public boolean canMoveY(double changeY){
		double newY = changeY + getY();
		
		if(changeY > 0){
			return !collidesWithTile(getX(), newY + getHeight()/2) &&
				   !collidesWithTile(getX()+getWidth()/2 -COLLISION_PADDING, newY + getHeight()/2) &&
				   !collidesWithTile(getX()-getWidth()/2 +COLLISION_PADDING, newY + getHeight()/2);
		} else if(changeY < 0){
			return !collidesWithTile(getX(), newY - getHeight()/2) &&
				   !collidesWithTile(getX()+getWidth()/2 -COLLISION_PADDING, newY - getHeight()/2) &&
				   !collidesWithTile(getX()-getWidth()/2 +COLLISION_PADDING, newY - getHeight()/2);
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
