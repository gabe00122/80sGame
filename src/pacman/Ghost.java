package pacman;
import java.awt.Graphics2D;

//import gabe00122.swinggames.Input;
import gabe00122.swinggames.Sprite;

public class Ghost extends Actor 
{
	private Sprite ghost1Sprite, ghost2Sprite, ghost3Sprite, ghost4Sprite, ghostScaredSprite;
	
	public Ghost()
	{
		super();
		setPosition(400, 400);
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
		
		//create a scared ghost for later
		ghostScaredSprite = new Sprite(Resources.ghostS);
		ghostScaredSprite.setSize(getWidth(), getHeight());
		
		
		ghost1Sprite.setPosition(getX(), getY());
		ghost2Sprite.setPosition(getX()+50, getY());
		ghost3Sprite.setPosition(getX()+100, getY());
		ghost4Sprite.setPosition(getX()-50, getY());
		//ghostScaredSprite.setPosition(getX()-100, getY());
		
		//will call once pacman starts moving
		//moveGhost(ghost1Sprite);
		//moveGhost(ghost2Sprite);
		//moveGhost(ghost3Sprite);
		//moveGhost(ghost4Sprite);
		
		
		
	}
	
	/**
	 * the way the ghosts move, different for each color (Blue, Red, Orange, Pink)
	 */
	public void moveGhost(Sprite g)
	{
		//when game starts, ghosts start moving
		if (g == ghost1Sprite)
		{
			//how ghost 1 will move
			ghost1Sprite.setPosition(getX(), getY());
		}
		else if (g == ghost2Sprite )
		{
			//how ghost 2 will move
			ghost2Sprite.setPosition(getX() - 50, getY()+ 100);
		}
		else if (g == ghost3Sprite )
		{
			//how ghost 3 will move
			ghost3Sprite.setPosition(getX() + 100, getY()+ 100);
		}
		else if (g == ghost4Sprite )
		{
			//how ghost 4 will move
			ghost2Sprite.setPosition(getX() -100, getY()+ 100);
		}
		
		
	}
	
	/**
	 * ghosts scatter if a power pellet has been eaten
	 */
	public void scatter()
	{
		//if power pellet consumed change to blue image (edibleGhost) and scatter
	}
	
	/**
	 * Turns ghosts blue if they are edible
	 * @param g
	 */
	public void edibleGhost(Graphics2D g)
	{
		setSize(25, 25);
		ghostScaredSprite = new Sprite(Resources.ghostS);
		ghostScaredSprite.setSize(getWidth(), getHeight());
	}
	
	/**
	 * draw the ghosts 
	 */
	public void draw(Graphics2D g) 
	{
		//drawing ghosts
		
		ghost1Sprite.draw(g);
		ghost2Sprite.draw(g);
		ghost3Sprite.draw(g);
		ghost4Sprite.draw(g);
		ghostScaredSprite.draw(g);
	}
	
	/**
	 * if a ghost collides with pacman, pacman will die, reset game
	 */
	public void collision()
	{
		
	}
	
	@Override
	public void update(double delta) 
	{
		
		
		
	}

}
