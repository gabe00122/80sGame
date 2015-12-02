package pacman;

import java.awt.Image;

public class PinkGhost extends Ghost{
	private static final double PINK_LEED = 3;
	
	/**
	 * Load ghost image from resource file.
	 */
	@Override
	public Image getGhostImage() {
		return Resources.ghostP;
	}

	@Override
	public double getCournerX() {
		return 0;
	}

	@Override
	public double getCournerY() {
		return 0;
	}

	@Override
	/**
	 * Get location of pacman and move in the direction of pacman.
	 */
	public void chasePacman() {
		Pacman pacman = getGame().getPacman();
		targetX = pacman.getX();
		targetY = pacman.getY();
		if(pacman.getDirection() == MovingActor.UP){
			targetY -= Maze.TILE_HEIGHT * PINK_LEED;
		}
		else if(pacman.getDirection() == MovingActor.DOWN){
			targetY += Maze.TILE_HEIGHT * PINK_LEED;
		}
		else if(pacman.getDirection() == MovingActor.LEFT){
			targetX -= Maze.TILE_WEIGHT * PINK_LEED;
		}
		else if(pacman.getDirection() == MovingActor.RIGHT){
			targetX += Maze.TILE_WEIGHT * PINK_LEED;
		}
		seekMovement();
	}
}
