package pacman;

import java.awt.Image;

public class OrangeGhost extends Ghost{
	
	@Override
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
	public void chasePacman() {
		Pacman pacman = getGame().getPacman();
		//more then 8 tiles away
		if(Math.pow(pacman.getX() - getX(), 2) + Math.pow(pacman.getY() - getY(), 2) >
			Math.pow(Maze.TILE_WEIGHT * 8, 2)){
			targetX = pacman.getX();
			targetY = pacman.getY();
		} else {
			System.out.println("to close");
			targetX = getCournerX();
			targetY = getCournerY();
		}
		seekMovement();
	}

}
