package pacman;
import java.awt.Graphics2D;

import pacman.MazeTile.TileType;

public class Maze {
	private MazeTile[][] tiles;
	public static final int TILE_WEIGHT = 25;
	public static final int TILE_HEIGHT = 25;
	
	private int mapW;
	private int mapH;
	
	public Maze(){
		loadDefaults();
	}
	
	public void draw(Graphics2D g){
		for(int y = 0;y < mapH;y++){
			for(int x = 0;x < mapW; x++){
				tiles[y][x].draw(g, this, x, y);
			}
		}
	}
	
	public MazeTile getTile(int x, int y){
		if(x >= 0 && x < mapW && y >= 0 && y < mapH){
			return tiles[y][x];
		} else {
			return null;
		}
	}
	
	public void loadDefaults(){
		mapW = 20;
		mapH = 20;
		tiles = new MazeTile[mapH][mapW];
		
		for(int y = 0;y < mapH;y++){
			for(int x = 0;x < mapW;x++){
				if(y % 2 == 0)
					tiles[y][x] = new MazeTile(TileType.WALL);
				else{
					tiles[y][x] = new MazeTile(TileType.SPACE);
				}
			}
		}
	}
}
