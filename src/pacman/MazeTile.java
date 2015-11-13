package pacman;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MazeTile {
	private TileType tileType;
	private static final BasicStroke stroke = new BasicStroke(3);
	
	public MazeTile(TileType type){
		tileType = type;
	}
	
	public MazeTile(char c){
		if(c == '#'){
			tileType = TileType.WALL;
		} else if(c == '.'){
			tileType = TileType.SPACE;
		}
	}
	
	public void draw(Graphics2D g, Maze maze, int x, int y){
		if(isVisible()){
			int x1 = (int) (x*Maze.TILE_WEIGHT + stroke.getLineWidth()/2);
			int y1 = (int) (y*Maze.TILE_HEIGHT + stroke.getLineWidth()/2);
			
			g.setColor(tileType.color);
			g.fillRect(x1, y1, Maze.TILE_WEIGHT, Maze.TILE_HEIGHT);
		}
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
		NULL, SPACE, WALL(Color.BLUE, true, true), DOOR(Color.GREEN, true, false);
		
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
