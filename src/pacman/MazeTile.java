package pacman;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class MazeTile {
	private TileType tileType;
	private static final BasicStroke stroke = new BasicStroke(3);
	
	public MazeTile(TileType type){
		tileType = type;
	}
	
	public void draw(Graphics2D g, Maze maze, int x, int y){
		if(tileType.isVisible){
			int x1 = (int) (x*Maze.TILE_WEIGHT + stroke.getLineWidth()/2);
			int y1 = (int) (y*Maze.TILE_HEIGHT + stroke.getLineWidth()/2);
			int x2 = (int) (x*Maze.TILE_WEIGHT + Maze.TILE_WEIGHT - stroke.getLineWidth()/2);
			int y2 = (int) (y*Maze.TILE_HEIGHT + Maze.TILE_HEIGHT - stroke.getLineWidth()/2);
			
			g.setColor(Color.ORANGE);
			g.fillRect(x1, y1, Maze.TILE_WEIGHT, Maze.TILE_HEIGHT);
			
			g.setColor(tileType.color);
			g.setStroke(stroke);
			
			boolean top = maze.getTile(x, y-1) != null && maze.getTile(x, y-1).isVisible();
			boolean bottom = maze.getTile(x, y+1) != null && maze.getTile(x, y+1).isVisible();
			boolean left = maze.getTile(x-1, y) != null && maze.getTile(x-1, y).isVisible();
			boolean right = maze.getTile(x+1, y) != null && maze.getTile(x+1, y).isVisible();
			
			if(!left || !right || !top || !bottom){
				if(left && right){
					g.drawLine(x1, y1, x2, y1);
					g.drawLine(x1, y2, x2, y2);
				}
			}
		}
	}
	
	private boolean isVisible(){
		return tileType.isVisible;
	}
	
	public enum TileType{
		SPACE, WALL(true, Color.BLUE, true, true), DOOR(true, Color.GREEN, true, false);
		
		private boolean isVisible;
		private Color color;
		private boolean playerCollide;
		private boolean ghostCollide;
		
		private TileType(boolean visible, Color color, boolean playerCollide, boolean ghostCollide){
			isVisible = visible;
			this.color = color;
			this.playerCollide = playerCollide;
			this.ghostCollide = ghostCollide;
		}
		
		private TileType(){
			isVisible = false;
			color = null;
			playerCollide = false;
			ghostCollide = false;
		}
	}
}
