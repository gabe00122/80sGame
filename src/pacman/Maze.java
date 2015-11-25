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
	
	/**
	 * @return the weight in tiles of the maze
	 */
	public int getMazeW(){
		return mapW;
	}
	
	/**
	 * @return the height in tiles of the maze
	 */
	public int getMazeH(){
		return mapH;
	}
	
	/**
	 * if outside of bounds returns a default tile of type null.
	 * 
	 * @param x in tiles starts at 0
	 * @param y in tiles starts at 0
	 * @return a tile
	 */
	public MazeTile getTile(int x, int y){
		if(x >= 0 && x < mapW && y >= 0 && y < mapH){
			return tiles[y][x];
		} else {
			return nullTile;
		}
	}
	
	/**
	 * 
	 * @param tMap example {"###", "#.#", "###"}
	 */
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
	
	/**
	 * gets a tile at the coordinate in pixels
	 */
	public MazeTile getTileAt(double x, double y){
		return getTile((int)(x/TILE_WEIGHT), (int)(y/TILE_HEIGHT));
	}
}
