package pacman;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




import swinggames.Sprite;

public class Ghost extends MovingActor 
{
	private Sprite ghost1Sprite, ghost2Sprite, ghost3Sprite, ghost4Sprite, ghostScaredSprite;
	private javax.swing.Timer timer;
	
	public Ghost()
	{
		super();
		setPosition(200, 225);
		setSize(50, 50);
		
		//creating ghosts (blue, red, pink, orange)
		ghost1Sprite = new Sprite(Resources.ghostB);
		ghost1Sprite.setSize(getWidth(), getHeight());
		ghost2Sprite = new Sprite(Resources.ghostR);
		ghost2Sprite.setSize(getWidth(), getHeight());
		ghost3Sprite = new Sprite(Resources.ghostP);
		ghost3Sprite.setSize(getWidth(), getHeight());
		ghost4Sprite = new Sprite(Resources.ghostO);
		ghost4Sprite.setSize(getWidth(), getHeight());
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
		ghost1Sprite.setPosition(getX(), getY());
		ghost2Sprite.setPosition(getX()+40, getY());
		ghost3Sprite.setPosition(getX()+80, getY());
		ghost4Sprite.setPosition(getX()+120, getY());
		start();
		
		//create a scared ghost for later
		//ghostScaredSprite = new Sprite(Resources.ghostS);
		//ghostScaredSprite.setSize(getWidth(), getHeight());
			
	}
	public void start()
	{
		leaveHome(ghost1Sprite);
		//leaveHome(ghost2Sprite);
		//leaveHome(ghost3Sprite);
		//leaveHome(ghost4Sprite);
	}
	public void moveG(Sprite g)
	{	
		
	}
	
	/**
	 * the way the ghosts move, different for each color (Blue, Red, Orange, Pink)
	 */

	public void chasePacman(Sprite g)
	{
		//use pacmans location to move towards a certain tile
		
		//when game starts, ghosts start moving

		if (g == ghost1Sprite)
		{
			//how ghost 1 will move "Blue"
			
		}
		else if (g == ghost2Sprite )
		{
			//how ghost 2 will move "Orange"
		}
		else if (g == ghost3Sprite )
		{
			//how ghost 3 will move "Pink"
		}
		else if (g == ghost4Sprite )
		{
			//how ghost 4 will move "Red"	
		}	

		

	}
	
	
	public void ghostScatter(Sprite g)
	{

		//go to a corner of the map each color has different corner
		
		//if power pellet consumed change to blue image (edibleGhost) and move toward a corner
		if (g == ghost1Sprite)
		{
			ghost1Sprite.setImage(Resources.ghostS);
			//move towards top right corner
		}
		else if (g == ghost2Sprite )
		{
			ghost2Sprite.setImage(Resources.ghostS);
			//move towards top left corner
		}
		else if (g == ghost3Sprite )
		{
			ghost3Sprite.setImage(Resources.ghostS);
			//move towards bottom left corner	
		}
		else if (g == ghost4Sprite )
		{
			ghost4Sprite.setImage(Resources.ghostS);
			//move towards bottom right corner
		}	
		

		//if power pellet consumed change to blue image (edibleGhost) and scatter

	}
	
	public void ghostFrightened(Sprite g)
	{

		//randomly decide which way to turn at each intersection
		if (g == ghost1Sprite)
		{
			ghost1Sprite.setImage(Resources.ghostS);
			//move towards top right corner
		}
		else if (g == ghost2Sprite )
		{
			ghost2Sprite.setImage(Resources.ghostS);
			//move towards top left corner
		}
		else if (g == ghost3Sprite )
		{
			ghost3Sprite.setImage(Resources.ghostS);
			//move towards bottom left corner	
		}
		else if (g == ghost4Sprite )
		{
			ghost4Sprite.setImage(Resources.ghostS);
			//move towards bottom right corner
		}	
		

		//setSize(25, 25);
		//ghostScaredSprite = new Sprite(Resources.ghostS);
		//ghostScaredSprite.setSize(getWidth(), getHeight());

	}
	
	public void leaveHome(Sprite g)
	{
		//set position outside home
		//ghost1Sprite.setPosition(275, 150);
		ghost1Sprite.setPosition(600, 100);
		//move(0, 150);
		
		
		
		//ghost2Sprite.setPosition(275, 150);
		//ghost3Sprite.setPosition(275, 150);
		//ghost4Sprite.setPosition(275, 150);
		
	}
	
	public void draw(Graphics2D g) 
	{
		//drawing ghosts

		
		/*ghost1Sprite.setPosition(getX(), getY());
		ghost2Sprite.setPosition(getX()+50, getY());
		ghost3Sprite.setPosition(getX()+100, getY());
		ghost4Sprite.setPosition(getX()+150, getY());*/
		//ghostScaredSprite.setPosition(getX()+125, getY());

		ghost1Sprite.draw(g);
		ghost2Sprite.draw(g);
		ghost3Sprite.draw(g);
		ghost4Sprite.draw(g);
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

		
		
		
	}

}
