package pacman;

import java.awt.Image;

public class OrangeGhost extends Ghost{
	
	/**
	 * Load ghost image from resource file.
	 * @Override
	 */
	public Image getGhostImage() {
		return Resources.ghostO;
	}

	@Override
	public double getCournerX() {
		return 0;
	}

	@Override
	public double getCournerY() {
		return Maze.TILE_HEIGHT * getGame().getMaze().getMazeH();
	}

	@Override
	/**
	 * Get pacmans location and move in that direction.
	 */
	public void chasePacman() {
		Pacman pacman = getGame().getPacman();
		//more then 8 tiles away
		if(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2) >
			Math.pow(Maze.TILE_WEIGHT * 8, 2)){
			targetX = pacman.getX();
			targetY = pacman.getY();
		} else {
			targetX = getCournerX();
			targetY = getCournerY();
		}
		seekMovement();
	}

}
