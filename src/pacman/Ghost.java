package pacman;
import java.awt.Graphics2D;
import java.util.List;

import swinggames.Sprite;

/**
 * This class a child of MovingActor and creates ghosts
 * for use in a Pacman game.
 */
public class Ghost extends MovingActor 
{
	private Sprite ghostSprite, ghostScaredSprite;
	private static final int BLUE_ID = 0, RED_ID = 1, PINK_ID = 2, ORANGE_ID = 3;
	private static final int SEEK_TIME = 5;
	private static final int SCATTER_TIME = 5;
	private static final int SCARE_TIME = 10;
	private static final int LEAVE_HOME_TIME = 5;	
	private int idNumber;
	private double targetX, targetY;
	private double cornerTargetX, cornerTargetY;
	private double homeX, homeY;
	private double seekTime;
	private double scaredTime;
	private double leaveHomeTime;
	private boolean eyeball;
	
	/**
	 * This method is used to create all ghosts and initializing the instance variables. 
	 * @param number The respective idNumber that correlates with each ghost. 
	 */
	public Ghost(int number)
	{
		super();
		setSize(50, 50);
		setSpeed(100);
		
		seekTime = 0;
		scaredTime = 0;
		leaveHomeTime = LEAVE_HOME_TIME;
		eyeball = false;
		
		idNumber = number;
		//creating ghosts (blue, red, pink, orange)
		if(number == BLUE_ID)
		{
			ghostSprite = new Sprite(Resources.ghostB);
		}
		
		if(number == RED_ID)
		{
			ghostSprite = new Sprite(Resources.ghostR);
		}
		
		if(number == PINK_ID)
		{
			ghostSprite = new Sprite(Resources.ghostP);
		}
		
		if(number == ORANGE_ID)
		{
			ghostSprite = new Sprite(Resources.ghostO);
		}
		ghostSprite.setSize(getWidth(), getHeight());
		
		//create a scared ghost for later
		ghostScaredSprite = new Sprite(Resources.ghostS);
		ghostScaredSprite.setSize(getWidth(), getHeight());
		
			
	}
	
	/**
	 * This method initializes where the ghosts will try to go once the game starts.
	 * @Override
	 */
	public void init() {
		homeX = getX();
		homeY = getY();
		
		if(idNumber == BLUE_ID)
		{
			cornerTargetX = Maze.TILE_WEIGHT;
			cornerTargetY = Maze.TILE_HEIGHT;
		}
		
		if(idNumber == RED_ID)
		{
			cornerTargetX = (getGame().getMaze().getMazeW()-1) * Maze.TILE_WEIGHT;
			cornerTargetY = Maze.TILE_HEIGHT;
			
		}
		
		if(idNumber == PINK_ID)
		{
			cornerTargetX = Maze.TILE_WEIGHT;
			cornerTargetY = (getGame().getMaze().getMazeH()-1) * Maze.TILE_HEIGHT;
		}
		
		if(idNumber == ORANGE_ID)
		{
			cornerTargetX = (getGame().getMaze().getMazeW()-1) * Maze.TILE_WEIGHT;
			cornerTargetY = (getGame().getMaze().getMazeH()-1) * Maze.TILE_HEIGHT;
		}
	}
	
	/**
	 * This method checks to see if the ghosts are still alive and redraws them when 
	 * they are scared.  It redraws the ghosts to normal once a power pellet has expired. 
	 * @param g Graphics2D component
	 */
	public void draw(Graphics2D g) 
	{
		if(eyeball){
			ghostToEyes();
			ghostSprite.setRotation(90);
			ghostSprite.setPosition(getX(), getY());	
			ghostSprite.draw(g);
		}
		else if(scaredTime <= 0){
			ghostSprite.setPosition(getX(), getY());	
			ghostSprite.setRotation(0);
			ghostSprite.draw(g);
		} else {
			if(scaredTime > 3 || (int)(scaredTime/0.5) % 2 == 0){
				ghostScaredSprite.setPosition(getX(), getY());	
				ghostScaredSprite.draw(g);
			}
		}
	}

	
	/**
	 * This method is called once per frame and updates the times needed by the ghosts.
	 * @Override
	 * @param delta The time since the last update.
	 */

	public void update(double delta) 
	{
		super.update(delta);
		
		
		seekTime -= delta;
		if(seekTime < -SCATTER_TIME){
			seekTime = SEEK_TIME;
		}
		
		leaveHomeTime -= delta;
		scaredTime -= delta;
	}
	
	/**
	 * 
	 */
	private void seekMovement(){
		List<Integer> directions = getDirectionChoses();
		if (homeX == getX() && homeY == getY())
		{
			eyesToGhost(idNumber);
		}
		if(directions.size() == 1){ //only one option
			setTargetDirection(directions.get(0));
		}
		else {
			//can't go back the way we came
			if(getDirection() == UP){
				directions.remove((Object)DOWN);
			} else if(getDirection() == DOWN){
				directions.remove((Object)UP);
			} else if(getDirection() == LEFT){
				directions.remove((Object)RIGHT);
			} else if(getDirection() == RIGHT){
				directions.remove((Object)LEFT);
			}
			
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
			
			setTargetDirection(maxDir);
		}
	}
	
	/**
	 * 
	 */
	private void fleeMovement(){
		List<Integer> directions = getDirectionChoses();
		
		if(directions.size() == 1){ //only one option
			setTargetDirection(directions.get(0));
		}
		else {
			//can't go back the way we came
			if(getDirection() == UP){
				directions.remove((Object)DOWN);
			} else if(getDirection() == DOWN){
				directions.remove((Object)UP);
			} else if(getDirection() == LEFT){
				directions.remove((Object)RIGHT);
			} else if(getDirection() == RIGHT){
				directions.remove((Object)LEFT);
			}
			
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
			
			setTargetDirection(minDir);
		}
	}
	
	/**
	 * 
	 * @param dir
	 * @param diffX
	 * @param diffY
	 * @return
	 */
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
	 * 
	 */
	public void scare(){
		scaredTime = SCARE_TIME;
	}
	/**
	 * 
	 */
	public void eat(){
		if(eyeball == false && isScared()){
			eyeball = true;
			setSpeed(200);
			setDirection(NONE);
			onNewDirection();
		}
	}
	
	/**
	 * 
	 */
	private void chasePacman(){
		targetX = getGame().getPacman().getX();
		targetY = getGame().getPacman().getY();
		seekMovement();
	}
	
	/**
	 * 
	 */
	private void fleePacman(){
		targetX = getGame().getPacman().getX();
		targetY = getGame().getPacman().getY();
		fleeMovement();
	}
	
	/**
	 * 
	 */
	private void leaveHome(){
		targetX = homeX;//  - Maze.TILE_WEIGHT;
		targetY = homeY;// + Maze.TILE_HEIGHT * 2;
		fleeMovement();
	}
	
	/**
	 * 
	 */
	private void returnHome(){
		targetX = homeX;
		targetY = homeY + Maze.TILE_HEIGHT;
		seekMovement();
		eyesToGhost(idNumber);
	}
	
	/**
	 * 
	 * @param idNumber
	 */
	private void eyesToGhost(int idNumber)
	{
		if(idNumber == BLUE_ID)
		{
			ghostSprite.setImage(Resources.ghostB);
		}
		
		if(idNumber == RED_ID)
		{
			ghostSprite.setImage(Resources.ghostR);
		}
		
		if(idNumber == PINK_ID)
		{
			ghostSprite.setImage(Resources.ghostP);
		}
		
		if(idNumber == ORANGE_ID)
		{
			ghostSprite.setImage(Resources.ghostO);
		}
		ghostSprite.setSize(getWidth(), getHeight());
	}
	
	/**
	 * 
	 */
	private void ghostToEyes()
	{
		ghostSprite.setImage(Resources.deadEyes);
		ghostSprite.setSize(15, 15);
	}
	
	/**
	 * 
	 */
	private void gotoCorner(){
		targetX = cornerTargetX;
		targetY = cornerTargetY;
		seekMovement();
	}
	
	/**
	 * 
	 */
	@Override
	public void onNewDirection(){
		super.onNewDirection();
		
		if(eyeball){				
			returnHome();
			if(Math.abs(homeX - getX()) < 50 && Math.abs(homeY - getY()) < 50){
				eyeball = false;
				leaveHomeTime = LEAVE_HOME_TIME;
				scaredTime = 0;
				setSpeed(100);
			}
		}
		else{
			if(leaveHomeTime > 0){
				leaveHome();
			}
			else if(scaredTime > 0){
				fleePacman();
			}else if(seekTime <= 0){
				gotoCorner();
			}
			else{
				chasePacman();
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isScared(){
		return scaredTime > 0;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean collidesWithTile(MazeTile t) 
	{
		if(leaveHomeTime > 0 || eyeball){
			return t.ghostCollide();
		} else{
			return t.playerCollides();
		}
	}
}

