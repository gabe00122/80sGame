package pacman;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import swinggames.Sprite;

/**
 * This class is a child of MovingActor and creates ghosts
 * for use in a Pacman game.
 */
public class Ghost extends MovingActor 
{
	private Sprite ghostSprite;
	private Image ghostImage, scaredImage, eyesImage;
	private static final int BLUE_ID = 0, RED_ID = 1, PINK_ID = 2, ORANGE_ID = 3;
	private static final int SEEK_TIME = 20;
	private static final int SCATTER_TIME = 7;
	private static final int SCARE_TIME = 10;
	private static final int LEAVE_HOME_TIME = 5;	
	private static final double SPEED = 100;
	private static final double EYEBALL_SPEED = 200;	
	
	private int idNumber;
	private double targetX, targetY;
	private double cornerTargetX, cornerTargetY;
	private double homeX, homeY;
	private double seekTime;
	private double scaredTime;
	private double leaveHomeTime;
	private boolean eyeballMode;
	/**
	 * This method is used to create all ghosts and initializing the instance variables. 
	 * @param number The respective idNumber that correlates with each ghost. Between one and four.
	 */
	public Ghost(int number)
	{
		super();
		setSize(50, 50);
		setSpeed(SPEED);
		
		seekTime = 0;
		scaredTime = 0;
		leaveHomeTime = LEAVE_HOME_TIME;
		eyeballMode = false;
		
		idNumber = number;
		//creating ghosts (blue, red, pink, orange)
		if(number == BLUE_ID)
		{
			ghostImage = Resources.ghostB;
		}
		
		if(number == RED_ID)
		{
			ghostImage = Resources.ghostR;
		}
		
		if(number == PINK_ID)
		{
			ghostImage = Resources.ghostP;
		}
		
		if(number == ORANGE_ID)
		{
			ghostImage = Resources.ghostO;
		}
		
		ghostSprite = new Sprite(ghostImage);
		ghostSprite.setSize(getWidth(), getHeight());
		
		scaredImage = Resources.ghostS;
		eyesImage = Resources.deadEyes;
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
			cornerTargetX = 0;
			cornerTargetY = 0;
		}
		
		if(idNumber == RED_ID)
		{
			cornerTargetX = (getGame().getMaze().getMazeW()) * Maze.TILE_WEIGHT;
			cornerTargetY = 0;
		}
		
		if(idNumber == PINK_ID)
		{
			cornerTargetX = 0;
			cornerTargetY = (getGame().getMaze().getMazeH()) * Maze.TILE_HEIGHT;
		}
		
		if(idNumber == ORANGE_ID)
		{
			cornerTargetX = (getGame().getMaze().getMazeW()) * Maze.TILE_WEIGHT;
			cornerTargetY = (getGame().getMaze().getMazeH()) * Maze.TILE_HEIGHT;
		}
	}
	
	/**
	 * This method checks to see if the ghosts are still alive and redraws them when 
	 * they are scared.  It redraws the ghosts to normal once a power pellet has expired. 
	 * @param g Graphics2D component
	 */
	public void draw(Graphics2D g) 
	{
		if(eyeballMode){
			ghostSprite.setImage(eyesImage);
			ghostSprite.setSize(getWidth(), getHeight());
			ghostSprite.setPosition(getX(), getY());
			ghostSprite.draw(g);
		}
		else if(scaredTime <= 0){
			ghostSprite.setImage(ghostImage);
			ghostSprite.setSize(getWidth(), getHeight());
			ghostSprite.setPosition(getX(), getY());
			ghostSprite.draw(g);
		} else {
			if(scaredTime > 3 || (int)(scaredTime/0.5) % 2 == 0){
				ghostSprite.setImage(scaredImage);
				ghostSprite.setSize(getWidth(), getHeight());
				ghostSprite.setPosition(getX(), getY());
				ghostSprite.draw(g);
			}
		}
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
		if(directions.size() == 1){ //only one option
			setDirection(directions.get(0));
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
			
			setDirection(maxDir);
		}
	}
	
	/**
	 * 
	 */
	private void fleeMovement(){
		List<Integer> directions = getDirectionChoses();
		
		if(directions.size() == 1){ //only one option
			setDirection(directions.get(0));
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
			
			setDirection(minDir);
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
	 * Set the ghost to scared mode
	 */
	public void scare(){
		scaredTime = SCARE_TIME;
	}
	
	/**
	 * ghost to eyeball mode
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

