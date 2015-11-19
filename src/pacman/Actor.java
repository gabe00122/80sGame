package pacman;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Actor {
	private PacmanGame game;
	protected Rectangle2D.Double rect;
	
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
	
	public boolean checkCollison(double x, double y, double w, double h){
		return getX()-getWidth()/2 < x+w/2 && getX()+getWidth()/2 > x-w/2 &&
			   getY()-getHeight()/2 < y+h/2 && getY()+getHeight()/2 > y-w/2;
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
