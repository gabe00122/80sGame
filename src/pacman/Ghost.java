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
	
	public void travel(Sprite g)
	{
		
		g.setPosition(chX, chY);
		
	}
	
	/**
	 * the way the ghosts move, different for each color (Blue, Red, Orange, Pink)
	 */
	public void moveGhost(Sprite g)
	{
		//when game starts, ghosts start moving
		if (g == ghost1Sprite)
		{
			//how ghost 1 will move "Blue"
			
			travel(ghost1Sprite);
			scatter();
		}
		else if (g == ghost2Sprite )
		{
			//how ghost 2 will move "Orange"
			travel(ghost2Sprite);
		}
		else if (g == ghost3Sprite )
		{
			//how ghost 3 will move "Pink"
			travel(ghost3Sprite);
		}
		else if (g == ghost4Sprite )
		{
			//how ghost 4 will move "Red"
			travel(ghost4Sprite);
		}	
	}
	
	/**
	 * ghosts scatter if a power pellet has been eaten
	 */
	public void scatter()
	{
		//if power pellet consumed change to blue image (edibleGhost) and scatter
		ghost1Sprite.setImage(Resources.ghostS);
		ghost2Sprite.setImage(Resources.ghostS);
		ghost3Sprite.setImage(Resources.ghostS);
		ghost4Sprite.setImage(Resources.ghostS);
	}
	
	/**
	 * Turns ghosts blue if they are edible
	 * @param g
	 */
	public void edibleGhost(Graphics2D g)
	{
		setSize(40, 40);
		
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
		//ghostScaredSprite.draw(g);
	}
	
	
	
	@Override
	public void update(double delta) 
	{
		
		//will call once pacman starts moving
		moveGhost(ghost1Sprite);
		
		//moveGhost(ghost2Sprite);
		//moveGhost(ghost3Sprite);
		//moveGhost(ghost4Sprite);
		
		
		/*ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				moveGhost(ghost1Sprite);
				//moveGhost(ghost2Sprite);
				//moveGhost(ghost3Sprite);
				//moveGhost(ghost4Sprite);
				
			}
		};
		
		final int DELAY = 1000; 
		// milliseconds between timer ticks
		Timer timer = new Timer(DELAY, listener);
		
		timer.start();
		*/
		
		
	}

}
