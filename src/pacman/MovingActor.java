package pacman;

public abstract class MovingActor extends Actor {
	public static final int NONE = -1, UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private static final double COLLISION_PADDING = 1.5;
	
	private double speed;
	private int direction;
	private int preferredDirection;
	
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
		switch (preferredDirection) {
		case UP:
			if(canMoveY(-speed * delta))
			{
				direction = preferredDirection;
			}
			break;
		case DOWN:
			if(canMoveY(speed * delta))
			{
				direction = preferredDirection;
			}
			break;
		case LEFT:
			if(canMoveX(-speed * delta))
			{
				direction = preferredDirection;
			}
			break;
		case RIGHT:
			if(canMoveX(speed * delta))
			{
				direction = preferredDirection;
			}
			break;
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
		while(!canMoveX(changeX)){
			changeX /= 2;
			if(changeX <= COLLISION_PADDING){
				direction = NONE;
				break;
			}
		}
		
		while(!canMoveY(changeY)){
			changeY /= 2;
			if(changeY <= COLLISION_PADDING){
				direction = NONE;
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
	
	private boolean collidesWithTile(double x, double y){
		return collidesWithTile(getGame().getMaze().getTileAt(x, y));
	}
	
	public boolean collidesWithTile(MazeTile t){
		return false;
	}
}
