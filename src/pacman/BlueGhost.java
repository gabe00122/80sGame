package pacman;

import java.awt.Image;

public class BlueGhost extends Ghost{
	private static final double BLUE_LEED = 3;
	private Ghost red;
	
	public BlueGhost(Ghost redGhost) {
		super();
		red = redGhost;
	}
	
	/**
	 * Load ghost image from resource file.
	 */
	@Override
	public Image getGhostImage() {
		return Resources.ghostB;
	}

	@Override
	public double getCournerX() {
		return getGame().getMaze().getMazeW() * Maze.TILE_WEIGHT;
	}

	@Override
	public double getCournerY() {
		return getGame().getMaze().getMazeH() * Maze.TILE_HEIGHT;
	}

	/**
	 * Get location of pacman and red ghost and move in the direction of pacman teaming with red ghost.
	 */
	public void chasePacman() {
		Pacman pacman = getGame().getPacman();
		
		targetX = pacman.getX();
		targetY = pacman.getY();
		if(pacman.getDirection() == MovingActor.UP){
			targetY -= Maze.TILE_HEIGHT * BLUE_LEED;
		}
		else if(pacman.getDirection() == MovingActor.DOWN){
			targetY += Maze.TILE_HEIGHT * BLUE_LEED;
		}
		else if(pacman.getDirection() == MovingActor.LEFT){
			targetX -= Maze.TILE_WEIGHT * BLUE_LEED;
		}
		else if(pacman.getDirection() == MovingActor.RIGHT){
			targetX += Maze.TILE_WEIGHT * BLUE_LEED;
		}
		
		targetX +=  targetX - red.getX();
		targetY += targetY - red.getY();
		
		seekMovement();
	}

}
