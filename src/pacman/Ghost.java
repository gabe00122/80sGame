package pacman;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//import Screen.TimerListener;
import swinggames.Sprite;

public class Ghost extends MovingActor 
{
	private Sprite ghostSprite, ghostScaredSprite;
	private javax.swing.Timer timer;
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
		
		timer = new javax.swing.Timer(30, new TimerListener()); //you don't really need this
		timer.start();
		start();
		
		//create a scared ghost for later
		//ghostScaredSprite = new Sprite(Resources.ghostS);
		//ghostScaredSprite.setSize(getWidth(), getHeight());
			
	}
	public void start()
	{
		leaveHome();
		//leaveHome(ghost2Sprite);
		//leaveHome(ghost3Sprite);
		//leaveHome(ghost4Sprite);
	}
	public void moveG()
	{	
		
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
		//ghost1Sprite.setPosition(275, 150);
		//ghost1Sprite.setPosition(600, 100);
		//move(0, 150);
		setDirection(UP);
		
		
		//ghost2Sprite.setPosition(275, 150);
		//ghost3Sprite.setPosition(275, 150);
		//ghost4Sprite.setPosition(275, 150);
		
	}
	
	public void draw(Graphics2D g) 
	{
		//drawing ghosts

		
		/* ghost2Sprite.setPosition(getX()+50, getY());
		ghost3Sprite.setPosition(getX()+100, getY());
		ghost4Sprite.setPosition(getX()+150, getY());*/
		//ghostScaredSprite.setPosition(getX()+125, getY());
		
		ghostSprite.setPosition(getX(), getY());
		ghostSprite.draw(g);
		//ghostScaredSprite.draw(g);
	}
	private class TimerListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			
			//call update every second
		}
	}
	
	
	@Override
	public void update(double delta) 
	{
		super.update(delta);
		//TODO use this method for everything, It's very important!
		
		
		
	}
	
	//sorry forgot to tell you about this
	@Override
	public boolean collidesWithTile(MazeTile t) {
		return t.ghostCollide();
	}

}
