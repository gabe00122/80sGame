package pacman;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MazeTile {
	private TileType tileType;
	private static final BasicStroke stroke = new BasicStroke(3);
	
	/**
	 * 
	 * @param type
	 */
	public MazeTile(TileType type){
		tileType = type;
	}
	
	/**
	 * 
	 * @param c the tile type
	 */
	public MazeTile(char c){
		switch (c) {
		case '#':
			tileType = TileType.WALL;
			break;
		case '.':
			tileType = TileType.PAC_DOT;
			break;
		case '*':
			tileType = TileType.SUPER_DOT;
			break;
		case 'D':
			tileType = TileType.DOOR;
			break;
		case 'P':
			tileType = TileType.PACMAN_SPAWN;
			break;
		case 'G':
			tileType = TileType.GHOST_SPAWN;
			break;
			
		default:
			tileType = TileType.SPACE;
			break;
		}
	}
	
	public void draw(Graphics2D g, Maze maze, int x, int y){
		int x1 = (int) (x*Maze.TILE_WEIGHT + stroke.getLineWidth()/2);
		int y1 = (int) (y*Maze.TILE_HEIGHT + stroke.getLineWidth()/2);
		
		if(isVisible()){
			g.setColor(tileType.color);
		} else {
			g.setColor(PacmanGame.BACKGROUND_COLOR);
		}
		
		g.fillRect(x1, y1, Maze.TILE_WEIGHT, Maze.TILE_HEIGHT);
	}
	
	public TileType getTileType(){
		return tileType;
	}
	
	public boolean isVisible(){
		return tileType.color != null;
	}
	
	public boolean playerCollides(){
		return tileType.playerCollide;
	}
	
	public boolean ghostCollide(){
		return tileType.ghostCollide;
	}
	
	public enum TileType{
		NULL, SPACE, PAC_DOT, SUPER_DOT, PACMAN_SPAWN, GHOST_SPAWN, WALL(Color.BLUE, true, true), DOOR(Color.GREEN, true, false);
		
		private Color color;
		private boolean playerCollide;
		private boolean ghostCollide;
		
		private TileType(Color color, boolean playerCollide, boolean ghostCollide){
			this.color = color;
			this.playerCollide = playerCollide;
			this.ghostCollide = ghostCollide;
		}
		
		private TileType(){
			color = null;
			playerCollide = false;
			ghostCollide = false;
		}
	}
}
