package pacman;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import pacman.MazeTile.TileType;
import swinggames.Game;
import swinggames.Input;

import java.awt.Color;
import java.awt.Font;

public class PacmanGame implements Game{
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	/**
	 * Most of the game creation, pausing the game, playing audio clips upon event phenomena, 
	 */
	
	private Input input;
	private List<Actor> actors;
	private Maze maze;
	private int dotCount;
	private Pacman pacman;
	private Ghost ghost;
	private int gameScore;
	private int lives;
	private boolean paused;
	private double startGameDelay = 4;
	private double deathDelay = 1.5;
	private double interDelay = 5;
	private int gameState = START;
	private static final int START = 0;
	private static final int PLAYING = 1;
	private static final int DEATH = 2;
	private static final int NEWLEVEL = 3;
	
	public PacmanGame() {
		maze = new Maze();
		maze.loadTextMap(Resources.map);
		Resources.openingSong.play();		//play introduction song
		actors = new ArrayList<>();
		dotCount = 0;
		gameScore = 0;	//
		paused = true;
		lives = 3;
		addPacmanAndGhosts();
		addPacDots();
	}
	
	public void reset()
	{
		/**
		 * Subtracts 1 life and runs method to play sound when pacman dies.
		 */
		lives--;
		DeathRun();

	}
	
	public void NextLife()
	{
		/**
		 * The works of an improved death reset.
		 */
		
		paused = true;
		removeActor(pacman);
		removeActor(ghost);
		addPacmanAndGhosts();
		
	}

	public void LifeBegin()
	{
		/**
		 * The current creation of maps and reset between levels and deaths.
		 */
		paused = true;
		maze = new Maze();
		maze.loadTextMap(Resources.map);;
		actors = new ArrayList<>();
		dotCount = 0;
		addPacmanAndGhosts();
		addPacDots();	
	}
	
	public void GameOver()
	{
		/**
		 * Pauses the game for the game over screen.
		 */
		//stop pacman and ghosts, write Game Over to screen.
		paused = true;
		
	}
		
	public Pacman getPacman(){
		return pacman;
		/**
		 * Returns pacman.
		 */
	}
	
	private void addPacDots(){
		/**
		 * adds pacman pellets to the maze.
		 */
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PAC_DOT){
					addActor(new PacDot(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT, false));
					dotCount++;
				} else if(tileType == TileType.SUPER_DOT){
					addActor(new PacDot(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT, true));
					dotCount++;
				}
			}
		}
	}
	
	public void removePacDot(){
		/**
		 * Removes pacman pellets from the maze when pacman eats them.
		 */
		Resources.wakaWaka.play();		//play when eating dots
		dotCount--;
		gameScore += 10;		//update score each time pellet consumed
		if(dotCount == 0)
		{
			NextLevel();
		}
	}
	
	public void addScore(int score){
		/**
		 * Method to add score.
		 */
		gameScore += score;
	}
	
	private void addPacmanAndGhosts()
	{
		/**
		 * Adds pacman and ghosts into the maze.
		 */
		int ghostCount = 0;
		Ghost redGhost = null;
		
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PACMAN_SPAWN)
				{
					pacman = new Pacman();
					addActor(pacman);
					pacman.setPosition(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT);
				}
				 else if(tileType == TileType.GHOST_SPAWN){
					Ghost ghost;
					switch (ghostCount++ % 4) {
					case 0:
						ghost = new RedGhost();
						redGhost = ghost;
						break;
					case 1:
						ghost = new OrangeGhost();
						break;
					case 2:
						ghost = new PinkGhost();
						break;
					default:
						ghost = new BlueGhost(redGhost);
					}
					ghost.setPosition(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT);
					addActor(ghost);
				}
			}
		}
	}
	
	public List<Ghost> getGhosts(){
		/**
		 * returns Array List of ghosts.
		 */
		List<Ghost> out = new ArrayList<>();
		for(Actor a: actors){
			if(a instanceof Ghost){
				out.add((Ghost)a);
			}
		}
		return out;
	}
	
	public List<Actor> checkCollisons(double x, double y, double w, double h){
		/**
		 * Collision check for our moving entities.
		 */
		List<Actor> out = new ArrayList<>();
		for(int i = 0;i < actors.size();i ++){
			Actor actor = actors.get(i);
			if(actor.checkCollison(x, y, w, h)){
				out.add(actor);
			}
		}
		return out;
	}

	@Override
	public void draw(Graphics2D g) {
		/**
		 * How we added text to our game (Game Over, Lives, Score)
		 */
		maze.draw(g);
		for(int i = actors.size() - 1; i >= 0; i--){
			actors.get(i).draw(g);
		}
		String life = String.valueOf(lives);
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		g.setColor(Color.BLACK);
		g.drawString("Lives: " + life, 480, 20);
		String score = String.valueOf(gameScore);

		//g.setColor(Color.BLUE);
		//g.drawString("SCORE: ", 505, 50);
		//g.drawString(score, 540, 80);
		g.drawString("SCORE: " + score, 50, 20);
		
		if (lives <= 0)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.BOLD, 80));
			g.drawString("GAME OVER", 60,  230);
		}

	}


	@Override
	public void update(double delta) {
		/**
		 * Updating positioning of our characters based on the state of the game.
		 */
		if (gameState == START)
		{
			startGameDelay -= delta;
			if (startGameDelay < 0)
			{
				gameState = PLAYING;
				startGameDelay = 4;
			}
		}
		
		if (gameState == PLAYING)
		{
			//Resources.siren.loop();
			for (int i = 0;i < actors.size();i++)
			{
				actors.get(i).update(delta);
			}
		}
		
		if (gameState == DEATH)
		{
			deathDelay -= delta;
			if (deathDelay < 0)
			{
				if (lives <= 0)
				{
					GameOver();
				}

				else
				{
					//MidRun();
					StartRun();
				}
			}
		}
		if (gameState == NEWLEVEL)
		{
			interDelay -= delta;
			if (interDelay < 0)
			{
				StartRun();
			}
		}
	}

	public void DeathRun()
	{
		/**
		 * Method to pause game and play sound of death, then change state of game to death.
		 */
		deathDelay = 1.5;
		Resources.pacmanDies.play();
		gameState = DEATH;
	}
		
	public void StartRun()
	{
		/**
		 * Method to pause game and play intro song, then changes game state to start.
		 */
		LifeBegin();
		startGameDelay = 4;
		Resources.openingSong.play();
		gameState = START;
	}
	
	public void MidRun()
	{
		/**
		 * The works of an improved method of resetting map after death.
		 */
		NextLife();
		startGameDelay = 4;
		Resources.openingSong.play();
		gameState = START;
	}

	public void NextLevel()
	{
		/**
		 * Method to pause game and play level victory song, then change game state to new level.
		 */
		interDelay = 5;
		Resources.intermission.play();
		gameState = NEWLEVEL;
	}
	
	public Maze getMaze(){
		return maze;
	}
	
	public void addActor(Actor actor){
		actor.setGame(this);
		actors.add(actor);
		actor.init();
	}
	
	public void removeActor(Actor actor){
		actors.remove(actor);
	}
		
	@Override
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput(){
		return input;
	}
}
