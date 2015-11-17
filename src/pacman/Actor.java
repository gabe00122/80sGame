package pacman;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Actor {
	private static final double COLLISION_PADDING = 2;
	
	private PacmanGame game;
	private Rectangle2D.Double rect;
	
	public Actor(){
		rect = new Rectangle2D.Double(0, 0, 0, 0);
	}
		
	public double getX() {
		return rect.x;
	}

	public void setX(double x) {
		rect.x = x;
	}
	
	public double getY() {
		return rect.y;
	}

	public void setY(double y) {
		rect.y = y;
	}

	public double getWidth() {
		return rect.width;
	}

	public void setWidth(double width) {
		rect.width = width;
	}

	public void move(double changeX, double changeY){
		rect.x += changeX;
		rect.y += changeY;
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
	
	public double getHeight() {
		return rect.height;
	}

	public void setHeight(double height) {
		rect.height = height;
	}
	
	public void setSize(double w, double h){
		rect.width = w;
		rect.height = h;
	}
	
	public void setPosition(double x, double y){
		rect.x = x;
		rect.y = y;
	}
	
	public void setGame(PacmanGame game){
		this.game = game;
	}
	
	public PacmanGame getGame(){
		return game;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
}
