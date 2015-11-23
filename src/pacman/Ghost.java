package pacman;
import java.awt.Graphics2D;
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
		dir = rand.nextInt(4);
		
		
		//when game starts, ghosts start moving
		
		if (idNumber == BLUE_ID)
		{
			//how ghost 1 will move "Blue"
			setDirection(dir);
			
		}
		else if (idNumber == ORANGE_ID)
		{
			//how ghost 2 will move "Orange"
			setDirection(dir);
			
		}
		else if (idNumber == PINK_ID)
		{
			//how ghost 3 will move "Pink"
			setDirection(dir);
		}
		else if (idNumber == RED_ID)
		{
			//how ghost 4 will move "Red"
			setDirection(dir);
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
		dir = rand.nextInt(4);
		setDirection(LEFT);
		targetX--;
		targetY--;
		
		if (targetX < 5)
		{
			targetX = getX()-15;
			setDirection(dir);
			
		}
		//setDirection(DOWN);
		/*if (targetY < 1)
		{
			targetY = getY()+15;
			setDirection(UP);
			
		}*/
		
		
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