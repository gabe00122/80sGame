package pacman;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * An actor is used used by all moving actors. 
 * Ghosts and pacman.
 *
 */
public abstract class Actor {
	private PacmanGame game;
	protected Rectangle2D.Double rect;
	
	/**
	 * Represents anything in pacman space, besides text and the like.
	 */
	public Actor(){
		rect = new Rectangle2D.Double(0, 0, 0, 0);
	}
	
	/**
	 * Get x location in game window.
	 * @return location in pixels
	 */
	public double getX() {
		return rect.x;
	}

	/**
	 * Set a new x location in game window.
	 * @param x in pixels
	 */
	public void setX(double x) {
		rect.x = x;
	}
	
	/**
	 * Get y location in game window.
	 * @return in pixels
	 */
	public double getY() {
		return rect.y;
	}

	/**
	 * Set a new y location in game window.
	 * @param y in pixels
	 */
	public void setY(double y) {
		rect.y = y;
	}

	/**
	 * Get width of screen object.
	 * @return size in pixels
	 */
	public double getWidth() {
		return rect.width;
	}
	
	/**
	 * Set width of screen object.
	 * @param width in pixels
	 */
	public void setWidth(double width) {
		rect.width = width;
	}

	/**
	 * Change the position by a given interval
	 * @param changeX
	 * @param changeY
	 */
	public void move(double changeX, double changeY){
		rect.x += changeX;
		rect.y += changeY;
	}
	
	/**
	 * @return in pixels
	 */
	public double getHeight() {
		return rect.height;
	}

	/**
	 * @param height in pixels
	 */
	public void setHeight(double height) {
		rect.height = height;
	}
	
	/**
	 * @param w in pixels
	 * @param h in pixels
	 */
	public void setSize(double w, double h){
		rect.width = w;
		rect.height = h;
	}
	
	/**
	 * Checks if the rectangle and the actors size intersect.
	 */
	public boolean checkCollison(double x, double y, double w, double h){
		return getX()-getWidth()/2 < x+w/2 && getX()+getWidth()/2 > x-w/2 &&
			   getY()-getHeight()/2 < y+h/2 && getY()+getHeight()/2 > y-w/2;
	}
	
	/**
	 * setX(x)
	 * setY(y)
	 */
	public void setPosition(double x, double y){
		rect.x = x;
		rect.y = y;
	}
	
	protected void setGame(PacmanGame game){
		this.game = game;
	}
	
	/**
	 * Gets the game this actor is inside of.
	 */
	public PacmanGame getGame(){
		return game;
	}
	
	/**
	 * Called right after actor is added to game.
	 */
	public abstract void init();
	
	/**
	 * draws the actor on the screen!! :)
	 * don't Modify any game logic here.
	 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * update game logic here.
	 * example: move a ghost.
	 * @param delta time in seconds after last call to update
	 */
	public abstract void update(double delta);
}
