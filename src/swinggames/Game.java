package swinggames;

import java.awt.Graphics2D;

/**
 * An interface meant to be used with GameDisplay.
 * can be used to create 2d games.
 */
public interface Game{
	
	/**
	 * This is called every frame.
	 * @param g the graphics object
	 */
	public void draw(Graphics2D g);
	
	/**
	 * called every frame used to update game logic.
	 * @param delta the time in seconds after the last call
	 */
	public void update(double delta);
	
	/**
	 * gives the game an input object which can be use to assess keyboard input.
	 * always called by GameDisplay
	 * @param input have keyboard input from gameDisplay
	 */
	public void setInput(Input input);
}
