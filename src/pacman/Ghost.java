package pacman;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

import swinggames.Sprite;

public class Ghost extends MovingActor 
{
	private Sprite ghostSprite, ghostScaredSprite;
	private static final int BLUE_ID = 0, RED_ID = 1, PINK_ID = 2, ORANGE_ID = 3;
	private int idNumber;
	private Random rand = new Random();
	private double targetX, targetY;
	private int dir;
	
	
	public Ghost(int number)
	{
		super();
		//setPosition(200, 225);
		setSize(50, 50);
		setSpeed(100);
		
		targetX = 0;
		targetY = 0;
		
		idNumber = number;
		targetX = getX()-10;
		targetY = getY()+10;
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
		//ghostScaredSprite = new Sprite(Resources.ghostS);
		//ghostScaredSprite.setSize(getWidth(), getHeight());
			
	}
	
	/**
	 * the way the ghosts move, different for each color (Blue, Red, Orange, Pink)
	 */

	public void chasePacman()
	{
		//use pacmans location to move towards a certain tile

		//dir = rand.nextInt(4);
		
		
		//when game starts, ghosts start moving
		
		if (idNumber == BLUE_ID)
		{
			//how ghost 1 will move "Blue"
			//dir = rand.nextInt(4);
		}
		else if (idNumber == ORANGE_ID)
		{
			//how ghost 2 will move "Orange"
			//dir = rand.nextInt(4);
			
		}
		else if (idNumber == PINK_ID)
		{
			//how ghost 3 will move "Pink"
			//dir = rand.nextInt(4);
		}
		else if (idNumber == RED_ID)
		{
			//how ghost 4 will move "Red"
			//dir = rand.nextInt(4);
		}	
	}
	
	
	public void ghostScatter(Sprite g)
	{

		//go to a corner of the map each color has different corner
		
		//if power pellet consumed change to blue image (edibleGhost) and move toward a corner
		if (idNumber == BLUE_ID)
		{
			
			//move towards top right corner
		}
		else if (idNumber == RED_ID)
		{
			
			//move towards top left corner
		}
		else if (idNumber == ORANGE_ID)
		{
			
			//move towards bottom left corner	
		}
		else if (idNumber == PINK_ID)
		{
			
			//move towards bottom right corner
		}	
		

		//if power pellet consumed change to blue image (edibleGhost) and scatter

	}
	
	public void ghostFrightened()
	{

		//randomly decide which way to turn at each intersection
		if (idNumber == BLUE_ID)
		{
			
			//move towards top right corner
		}
		else if (idNumber == RED_ID)
		{
			
			//move towards top left corner
		}
		else if (idNumber == ORANGE_ID)
		{
			
			//move towards bottom left corner	
		}
		else if (idNumber == PINK_ID)
		{
			
			//move towards bottom right corner
		}		
		

		//setSize(25, 25);
		//ghostScaredSprite = new Sprite(Resources.ghostS);
		//ghostScaredSprite.setSize(getWidth(), getHeight());

	}
	
	public void leaveHome()
	{
		//set position outside home
		ghostSprite.setPosition(275, 150);
		
		
	}
	
	public void draw(Graphics2D g) 
	{
		ghostSprite.setPosition(getX(), getY());	//275, 150
		ghostSprite.draw(g);
		//ghostScaredSprite.draw(g);
	}

	
	@Override
	public void update(double delta) 
	{
		super.update(delta);
		//chasePacman();
		
		//setDirection(dir);
		//targetX-=5;
		//targetY--;
		
		//if (targetX < 5)
		//{
			//targetX = getX()-15;
			//setDirection(dir);
			
		//}
		//setDirection(DOWN);
		/*if (targetY < 1)
		{
			targetY = getY()+15;
			setDirection(UP);
			
		}*/	
		
	}
	
	private void randomMovement(){
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
			if(directions.size() > 0){
				setDirection(directions.get(rand.nextInt(directions.size())));
			}
		}
	}
	
	@Override
	public void onNewDirection(){
		super.onNewDirection();
		//only get's called when there is a new direction to move in
		
		randomMovement();
	}
	
	@Override
	public boolean collidesWithTile(MazeTile t) 
	{
		return t.ghostCollide();
	}

}



//System.out.println("x: "+ x + " y: "+y);
/*while (collidesWithTile(getGame().getMaze().getTileAt(x, y)) == true)		
{
	System.out.println("Changing Direction to " + dir);
	setDirection(dir);
	//System.out.println(dir);	
}*/