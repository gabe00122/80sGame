package pacman;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import pacman.MazeTile.TileType;
import swinggames.Game;
import swinggames.Input;

import java.awt.Color;
import java.awt.Font;

public class PacmanGame implements Game{
	private Input input;
	private List<Actor> actors;
	private Maze maze;
	private int dotCount;
	private Pacman pacman;
	private int gameScore;
	
	public PacmanGame() {
		maze = new Maze();
		maze.loadTextMap(Resources.map);
		Resources.openingSong.play();		//play introduction song
		actors = new ArrayList<>();
		dotCount = 0;
		gameScore = 0;	//
		addPacmanAndGhosts();
		addPacDots();
	}
	
	public Pacman getPacman(){
		return pacman;
	}
	
	private void addPacDots(){
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PAC_DOT){
					addActor(new PacDot(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT, false));
					dotCount++;
				} else if(tileType == TileType.SUPER_DOT){
					addActor(new PacDot(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT, true));
					dotCount++;
				}
			}
		}
	}
	
	public void removePacDot(){
		Resources.wakaWaka.play();		//play when eating dots
		dotCount--;
		gameScore += 10;		//update score each time pellet consumed
		if(dotCount == 0)
		{
			addPacDots();
		}
	}
	
	private void addPacmanAndGhosts(){
		int ghostCount = 0;
		
		for(int y = 0;y < maze.getMazeH(); y++){
			for(int x = 0; x < maze.getMazeW(); x++){
				TileType tileType = maze.getTile(x, y).getTileType();
				if(tileType == TileType.PACMAN_SPAWN){
					pacman = new Pacman();
					addActor(pacman);
					pacman.setPosition(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT);
				} else if(tileType == TileType.GHOST_SPAWN){
					Ghost ghost = new Ghost(ghostCount++);
					addActor(ghost);
					ghost.setPosition(x*Maze.TILE_HEIGHT, y*Maze.TILE_WEIGHT);
				}
			}
		}
	}
	
	public List<Ghost> getGhosts(){
		List<Ghost> out = new ArrayList<>();
		for(Actor a: actors){
			if(a instanceof Ghost){
				out.add((Ghost)a);
			}
		}
		return out;
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
		for(int i = actors.size() - 1; i >= 0; i--){
			actors.get(i).draw(g);
		}
		String score = String.valueOf(gameScore);
		g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		g.setColor(Color.BLUE);
		g.drawString("SCORE: ", 510, 50);
		g.drawString(score, 540, 80);
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
		actor.init();
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
