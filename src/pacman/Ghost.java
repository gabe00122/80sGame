package pacman;
import java.awt.Graphics2D;


import swinggames.Sprite;

public class Ghost extends MovingActor 
{
	private Sprite ghostSprite, ghostScaredSprite;
	private static final int BLUE_ID = 0, RED_ID = 1, PINK_ID = 2, ORANGE_ID = 3;
	private int idNumber;
	
	
	public Ghost(int number)
	{
		super();
		//setPosition(200, 225);
		setSize(50, 50);
		setSpeed(100);
		
		idNumber = number;
		
		//creating ghosts (blue, red, pink, orange)
		if(number == BLUE_ID){
			ghostSprite = new Sprite(Resources.ghostB);
		}
		
		if(number == RED_ID){
			ghostSprite = new Sprite(Resources.ghostR);
		}
		
		if(number == PINK_ID){
			ghostSprite = new Sprite(Resources.ghostP);
		}
		
		if(number == ORANGE_ID){
			ghostSprite = new Sprite(Resources.ghostO);
		}
		ghostSprite.setSize(getWidth(), getHeight());
		
		
		leaveHome();
		
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
		
		//when game starts, ghosts start moving

		if (idNumber == BLUE_ID)
		{
			//how ghost 1 will move "Blue"
			
			
		}
		else if (idNumber == ORANGE_ID)
		{
			//how ghost 2 will move "Orange"
			
		}
		else if (idNumber == PINK_ID)
		{
			//how ghost 3 will move "Pink"
			
		}
		else if (idNumber == RED_ID)
		{
			//how ghost 4 will move "Red"
			
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
		//ghostSprite.setPosition(275, 150);
		//chasePacman();
		//setDirection(UP);
		//setDirection(LEFT);
		//setDirection(DOWN);
		//setSpeed(150.0);
		
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
		//use this method for everything, It's very important!
		
	
	}
	
	@Override
	public boolean collidesWithTile(MazeTile t) {
		return t.ghostCollide();
	}

}
