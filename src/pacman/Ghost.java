package pacman;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Random;

import swinggames.Sprite;

/**
 * This class is a child of MovingActor and creates ghosts
 * for use in a Pacman game.
 */
public abstract class Ghost extends MovingActor 
{
	private Sprite ghostSprite;
	private Image ghostImage, scaredImage, eyesImage;
	private static final int SEEK_TIME = 20;
	private static final int SCATTER_TIME = 7;
	private static final int SCARE_TIME = 10;
	private static final int LEAVE_HOME_TIME = 2;	
	private static final double SPEED = 100;
	private static final double EYEBALL_SPEED = 300;	
	
	protected double targetX;
	protected double targetY;
	private double cornerTargetX, cornerTargetY;
	private double homeX, homeY;
	private double seekTime;
	private double scaredTime;
	private double leaveHomeTime;
	private boolean eyeballMode;
	private Random rand;
	
	/**
	 * This method is used to create all ghosts and initializing the instance variables. 
	 * @param number The respective idNumber that correlates with each ghost. Between one and four.
	 */
	public Ghost()
	{
		super();
		setSize(50, 50);
		setSpeed(SPEED);
		
		seekTime = 0;
		scaredTime = 0;
		leaveHomeTime = LEAVE_HOME_TIME;
		eyeballMode = false;
		
		//creating ghosts (blue, red, pink, orange)
		ghostImage = getGhostImage();
		
		ghostSprite = new Sprite(ghostImage);
		ghostSprite.setSize(getWidth(), getHeight());
		
		scaredImage = Resources.ghostS;
		eyesImage = Resources.deadEyes;
		
		rand = new Random();
	}
	/**
	 * Load ghost images.
	 */
	public abstract Image getGhostImage();
	
	/**
	 * This method initializes where the ghosts home and destination 
	 * are located.
	 * @Override
	 */
	public void init() {
		homeX = getX();
		homeY = getY();
		
		cornerTargetX = getCournerX();
		cornerTargetY = getCournerY();
	}
	
	public abstract double getCournerX();
	
	public abstract double getCournerY();
	
	/**
	 * This method checks to see if the ghosts are still alive and redraws them when 
	 * they are scared.  It redraws the ghosts to normal once a power pellet has expired. 
	 * @param g Graphics2D component
	 */
	public void draw(Graphics2D g) 
	{
		ghostSprite.setPosition(getX(), getY());
		
		if(eyeballMode){
			ghostSprite.setImage(eyesImage);
			ghostSprite.draw(g);
		}
		else if(scaredTime <= 0){
			ghostSprite.setImage(ghostImage);
			ghostSprite.draw(g);
		} else {
			if(scaredTime > 3 || (int)(scaredTime/0.5) % 2 == 0){
				ghostSprite.setImage(scaredImage);
				ghostSprite.draw(g);
			}
		}
		
		//g.drawRect((int)(targetX-5), (int)(targetY-5), 10, 10);
	}	
	
	/**
	 * This method is called once per frame and updates the times needed by the ghosts.
	 * @Override
	 * @param delta The time since the last update.
	 */
	@Override
	public void update(double delta) 
	{
		super.update(delta);
		
		if(!eyeballMode && leaveHomeTime < 0 && scaredTime < 0){
			seekTime -= delta;
			if(seekTime < -SCATTER_TIME){
				seekTime = SEEK_TIME;
			}
		}
		
		leaveHomeTime -= delta;
		scaredTime -= delta;
	}
	

	protected void seekMovement(){
		List<Integer> directions = getDirectionChoses();
		if(directions.size() == 1){ //only one option
			setDirection(directions.get(0));
		}
		else {
			//can't go back the way we came
			removeBackward(directions);
			
			double diffX = targetX - getX();
			double diffY = targetY - getY();
			
			double maxScore = getDirectionScore(directions.get(0), diffX, diffY);
			int maxDir = directions.get(0);
			
			for(int i: directions){
				double newScore = getDirectionScore(i, diffX, diffY);
				if(newScore > maxScore){
					maxScore = newScore;
					maxDir = i;
				}
			}
			
			setDirection(maxDir);
		}
	}
	
	private void randomMovement(){
		List<Integer> directions = getDirectionChoses();
		
		if(directions.size() == 1){ //only one option
			setDirection(directions.get(0));
		}
		else {
			//can't go back the way we came
			removeBackward(directions);
			
			//random movement
			setDirection(directions.get(rand.nextInt(directions.size())));
		}
	}
	
	private void fleeMovement(){
		List<Integer> directions = getDirectionChoses();
		
		if(directions.size() == 1){ //only one option
			setDirection(directions.get(0));
		}
		else {
			//can't go back the way we came
			removeBackward(directions);
			
			double diffX = targetX - getX();
			double diffY = targetY - getY();
			
			double minScore = getDirectionScore(directions.get(0), diffX, diffY);
			int minDir = directions.get(0);
			
			for(int i: directions){
				double newScore = getDirectionScore(i, diffX, diffY);
				if(newScore < minScore){
					minScore = newScore;
					minDir = i;
				}
			}
			
			setDirection(minDir);
		}
	}
	
	private void removeBackward(List<Integer> directions){
		if(getDirection() == UP){
			directions.remove((Object)DOWN);
		} else if(getDirection() == DOWN){
			directions.remove((Object)UP);
		} else if(getDirection() == LEFT){
			directions.remove((Object)RIGHT);
		} else if(getDirection() == RIGHT){
			directions.remove((Object)LEFT);
		}
	}
	
	private double getDirectionScore(int dir, double diffX, double diffY){
		double score;
		if(dir == LEFT){
			score = -diffX;
		}
		else if(dir == RIGHT){
			score = diffX;
		}
		else if(dir == UP){
			score = -diffY;
		}
		else{
			score = diffY; 
		}
		
		return score;
	}
	
	/**
	 * Set the scare time for the ghosts.
	 */
	public void scare(){
		scaredTime = SCARE_TIME;
	}
	
	/**
	 * This method changes the status of a ghost to eyeballs.
	 */
	public void turnToEyeballMode(){
		if(eyeballMode == false && isScared()){
			eyeballMode = true;
			setSpeed(EYEBALL_SPEED);
			haltMovment();
			onNewDirection();
			getGame().addScore(400);
		}
	}
	
	/**
	 * Get pacmans location and move in that direction.
	 */
	public abstract void chasePacman();
	
	
	protected void leaveHome(){
		targetX = homeX;//  - Maze.TILE_WEIGHT;
		targetY = homeY;// + Maze.TILE_HEIGHT * 2;
		fleeMovement();
	}
	
	
	private void returnHome(){
		targetX = homeX;
		targetY = homeY;
		seekMovement();
	}

	
	private void gotoCorner(){
		targetX = cornerTargetX;
		targetY = cornerTargetY;
		seekMovement();
	}
	

	@Override
	public void onNewDirection(){
		super.onNewDirection();
		
		if(eyeballMode){				
			returnHome();
			if(Math.abs(homeX - getX()) < 50 && Math.abs(homeY - getY()) < 50){
				eyeballMode = false;
				leaveHomeTime = LEAVE_HOME_TIME;
				scaredTime = 0;
				setSpeed(SPEED);
			}
		}
		else{
			if(leaveHomeTime > 0){
				leaveHome();
			}
			else if(scaredTime > 0){
				randomMovement();
			}else if(seekTime <= 0){
				gotoCorner();
			}
			else{
				chasePacman();
			}
		}
	}
	
	/**
	 * This method returns the time left before changing back to a regular ghost.
	 * @return time left before scared ghost changes back to regular ghost.
	 */
	public boolean isScared(){
		return scaredTime > 0;
	}
	/**
	 * 
	 * @return 
	 */
	public boolean isEyeball(){
		return eyeballMode;
	}
	
	@Override
	public boolean collidesWithTile(MazeTile t) 
	{
		if(leaveHomeTime > 0 || eyeballMode){
			return t.ghostCollide();
		} else{
			return t.playerCollides();
		}
	}
}

