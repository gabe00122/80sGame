package pacman;
import java.awt.Graphics2D;

public class Maze {
	private MazeTile[][] tiles;
	private static final int TILE_WEIGHT = 10;
	private static final int TILE_HEIGHT = 10;
	
	public Maze(){
		
	}
	
	public void draw(Graphics2D g){
		for(int i = 0;i < tiles.length;i++){
			for(int j = 0;j < tiles[i].length; j++){
				tiles[i][j].draw(g);
			}
		}
	}
	
	public void loadDefaults(){
		tiles = new MazeTile[20][20];
	}
}
