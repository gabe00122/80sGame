package pacman;
import java.awt.Graphics2D;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
import java.util.Random;

//import javax.swing.Timer;

//import gabe00122.swinggames.Input;
//import gabe00122.swinggames.Input;
import gabe00122.swinggames.Sprite;

public class Ghost extends Actor 
{
	private Sprite ghost1Sprite, ghost2Sprite, ghost3Sprite, ghost4Sprite, ghostScaredSprite;
	//private Random gen;
	private int chX = 100;	//gen.nextInt(400);
	private int chY = 250;	//chX;//gen.nextInt(400);
	
	
	
	
	public Ghost()
	{
		super();
		setPosition(220, 220);
		setSize(40, 40);
		
		
		//creating ghosts (blue, red, pink, orange)
		ghost1Sprite = new Sprite(Resources.ghostB);
		ghost1Sprite.setSize(getWidth(), getHeight());
		ghost2Sprite = new Sprite(Resources.ghostO);
		ghost2Sprite.setSize(getWidth(), getHeight());
		ghost3Sprite = new Sprite(Resources.ghostP);
		ghost3Sprite.setSize(getWidth(), getHeight());
		ghost4Sprite = new Sprite(Resources.ghostR);
		ghost4Sprite.setSize(getWidth(), getHeight());
	
		
		//adding ghosts to the box on the map // change this later to position them where a G is found on map
		ghost1Sprite.setPosition(getX(), getY());
		ghost2Sprite.setPosition(getX()+40, getY());
		ghost3Sprite.setPosition(getX()+80, getY());
		ghost4Sprite.setPosition(getX()+120, getY());
		//ghostScaredSprite.setPosition(getX()-100, getY());	
	}
	
	
	
	/**
	 * the way the ghosts move, different for each color (Blue, Red, Orange, Pink)
	 */
	public void chasePacman(Sprite g)
	{
		//use paacmans location to move towards a certain tile
		
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
	
	/**
	 * ghosts scatter if a power pellet has been eaten
	 */
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
		//ghostScaredSprite.draw(g);
	}
	
	
	
	@Override
	public void update(double delta) 
	{
		
		//if no power pellets eaten chase pacman
		chasePacman(ghost1Sprite);
		//chasePacman(ghost2Sprite);
		//chasePacman(ghost3Sprite);
		//chasePacman(ghost4Sprite);
		
		//if pellet eaten scatter
		//ghostScatter(ghost1Sprite);
		//ghostScatter(ghost2Sprite);
		//ghostScatter(ghost3Sprite);
		//ghostScatter(ghost4Sprite);
		
		//will call once pacman starts moving
		
		
		
		
		
		
	}

}
