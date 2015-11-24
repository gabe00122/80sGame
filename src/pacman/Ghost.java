package pacman;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.List;
import swinggames.Sprite;

/**
 * 
 */
public class Ghost extends MovingActor 
{
	private Sprite ghostSprite, ghostScaredSprite;
	private static final int BLUE_ID = 0, RED_ID = 1, PINK_ID = 2, ORANGE_ID = 3;
	private static final int SEEK_TIME = 5;
	private static final int SCATTER_TIME = 5;
	private static final int SCARE_TIME = 10;
	private int idNumber;
	private double targetX, targetY;
	private double cornerTargetX, cornerTargetY;
	private double seekTime;
	private double scaredTime;
	/**
	 * 
	 */
	public Ghost(int number)
	{
		super();
		setSize(50, 50);
		setSpeed(100);
		
		seekTime = 0;
		scaredTime = 0;
		
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
	
	@Override
	public void init() {
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
	
	public void draw(Graphics2D g) 
	{
		if(scaredTime <= 0){
			ghostSprite.setPosition(getX(), getY());	//275, 150
			ghostSprite.draw(g);
		} else {
			if(scaredTime > 3 || (int)(scaredTime/0.5) % 2 == 0){
				ghostScaredSprite.setPosition(getX(), getY());	//275, 150
				ghostScaredSprite.draw(g);
			}
		}
		//ghostScaredSprite.draw(g);
	}

	/**
	 * 
	 */
	@Override
	public void update(double delta) 
	{
		super.update(delta);
		
		
		seekTime -= delta;
		if(seekTime < -SCATTER_TIME){
			seekTime = SEEK_TIME;
		}
		
		if(scaredTime > 0){
			setSpeed(150);
			scaredTime -= delta;
		} else{
			setSpeed(100);
		}
	}
	
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
			
			//random movement
			
			directions.sort(new DirectionSorter());
			setDirection(directions.get(0));
		}
	}
	
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
			
			//random movement
			
			directions.sort(new DirectionSorter());
			setDirection(directions.get(directions.size()-1));
		}
	}
	
	public void scare(){
		scaredTime = SCARE_TIME;
	}
	
	@Override
	public void onNewDirection(){
		super.onNewDirection();
		
		if(seekTime > 0){
			targetX = getGame().getPacman().getX();
			targetY = getGame().getPacman().getY();
			if(scaredTime > 0){
				fleeMovement();
			} else {
				seekMovement();
			}
		} else {
			targetX = cornerTargetX;
			targetY = cornerTargetY;
			seekMovement();
		}
		//only get's called when there is a new direction to move in
		//targetX = getGame().getPacman().getX();
		//targetY = getGame().getPacman().getY();
		
		//targetMovement();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean collidesWithTile(MazeTile t) 
	{
		return t.ghostCollide();
	}
	
	private class DirectionSorter implements Comparator<Integer>{
		private double diffX, diffY;
		public DirectionSorter(){
			diffX = targetX - getX();
			diffY = targetY - getY();
		}
		
		@Override
		public int compare(Integer o1, Integer o2) {
			double score1 = 0, score2 = 0;
			if(o1 == LEFT){
				score1 = diffX;
			}
			else if(o1 == RIGHT){
				score1 = -diffX;
			}
			else if(o1 == UP){
				score1 = diffY;
			}
			else if(o1 == DOWN){
				score1 = -diffY; 
			}
			
			if(o2 == LEFT){
				score2 = diffX;
			}
			else if(o2 == RIGHT){
				score2 = -diffX;
			}
			else if(o2 == UP){
				score2 = diffY;
			}
			else if(o2 == DOWN){
				score2 = -diffY; 
			}
			
			if(score1 > score2){
				return 1;
			} else if(score1 < score2){
				return -1;
			} else {
				return 0;
			}
		}
	}
}

//System.out.println("x: "+ x + " y: "+y);
/*while (collidesWithTile(getGame().getMaze().getTileAt(x, y)) == true)		
{
	System.out.println("Changing Direction to " + dir);
	setDirection(dir);
	//System.out.println(dir);	
}*/