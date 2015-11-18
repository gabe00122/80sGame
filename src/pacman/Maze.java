package pacman;
import java.awt.Graphics2D;

import pacman.MazeTile.TileType;

public class Maze {
	private MazeTile[][] tiles;
	public static final int TILE_WEIGHT = 25;
	public static final int TILE_HEIGHT = 25;
	
	private int mapW;
	private int mapH;
	
	private MazeTile nullTile;
	
	public Maze(){
		nullTile = new MazeTile(TileType.NULL);
	}
	
	public void draw(Graphics2D g){
		for(int y = 0;y < mapH;y++){
			for(int x = 0;x < mapW; x++){
				tiles[y][x].draw(g, this, x, y);
			}
		}
	}
	
	public int getMazeW(){
		return mapW;
	}
	
	public int getMazeH(){
		return mapH;
	}
	
	public MazeTile getTile(int x, int y){
		if(x >= 0 && x < mapW && y >= 0 && y < mapH){
			return tiles[y][x];
		} else {
			return nullTile;
		}
	}
	
	public void loadTextMap(String[] tMap){
		mapH = tMap.length;
		mapW = tMap[0].length();
		
		tiles = new MazeTile[mapH][mapW];
		
		for(int y = 0; y < mapH; y++){
			for(int x = 0; x < mapW; x++){
				tiles[y][x] = new MazeTile(tMap[y].charAt(x));
			}
		}
	}
	
	public MazeTile getTileAt(double x, double y){
		return getTile((int)(x/TILE_WEIGHT), (int)(y/TILE_HEIGHT));
	}
}
