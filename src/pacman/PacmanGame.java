package pacman;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import gabe00122.swinggames.Game;
import gabe00122.swinggames.Input;
import pacman.MazeTile.TileType;

public class PacmanGame implements Game{
	private Input input;
	private List<Actor> actors;
	private Maze maze;
	private int dotCount;
	
	public PacmanGame() {
		maze = new Maze();
		maze.loadTextMap(Resources.map);
		
		actors = new ArrayList<>();
		dotCount = 0;
		
		addPacmanAndGhosts();
		addPacDots();
		
		
		addActor(new Ghost());
	}
	
	private void addPacDots(){
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PAC_DOT){
					addActor(new PacDot(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT));
					dotCount++;
				}
			}
		}
	}
	
	private void addPacmanAndGhosts(){
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PACMAN_SPAWN){
					Pacman pacman = new Pacman();
					addActor(pacman);
					pacman.setPosition(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT);
				}
			}
		}
	}
	
	public List<Actor> checkCollisons(double x, double y, double w, double h){
		List<Actor> out = new ArrayList<>();
		for(int i = 0;i < actors.size();i ++){
			Actor actor = actors.get(i);
			if(actor.checkCollison(x, y, w, h)){
				out.add(actor);
			}
		}
		return out;
	}
	
	@Override
	public void draw(Graphics2D g) {
		maze.draw(g);
		for(int i = 0;i < actors.size();i++){
			actors.get(i).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		for(int i = 0;i < actors.size();i++){
			actors.get(i).update(delta);
		}
	}
	
	public Maze getMaze(){
		return maze;
	}
	
	public void addActor(Actor actor){
		actor.setGame(this);
		actors.add(actor);
	}
	
	public void removeActor(Actor actor){
		actors.remove(actor);
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput(){
		return input;
	}
}
