package pacman;

import java.awt.Image;

public class RedGhost extends Ghost{

	/**
	 * Load ghost image from resource file.
	 * @Override
	 */
	public Image getGhostImage() {
		return Resources.ghostR;
	}

	@Override
	public double getCournerX() {
		return getGame().getMaze().getMazeW() * Maze.TILE_WEIGHT;
	}

	@Override
	public double getCournerY() {
		return 0;
	}

	@Override
	/**
	 * Get pacmans location and move in that direction.
	 */
	public void chasePacman() {
		Pacman pacman = getGame().getPacman();
		targetX = pacman.getX();
		targetY = pacman.getY();
		seekMovement();
	}

}
