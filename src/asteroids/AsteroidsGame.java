package asteroids;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gabe00122.swinggames.Game;
import gabe00122.swinggames.Input;

public class AsteroidsGame implements Game{
	private Input input;
	private List<Actor> actors;
	private double mapW = 800;
	private double mapH = 800;
	private Random rand;
	
	
	public AsteroidsGame() {
		actors = new ArrayList<>();
		rand = new Random();
		
		
		addActor(new Ship());
		addActor(new Asteroid());
		addActor(new Asteroid());
		addActor(new Asteroid());
	}
	
	public Random getRandom(){
		return rand;
	}
	
	@Override
	public void draw(Graphics2D g) {
		for(int i = 0;i < actors.size();i++){
			actors.get(i).draw(g);
		}
	}

	@Override
	public void update(double delta) {
		for(int i = 0;i < actors.size();i++){
			Actor actor = actors.get(i);
			actor.update(delta);
			
			if(actor.getX() > mapW){
				actor.move(-mapW, 0);
			} else if(actor.getX() < 0){
				actor.move(mapW, 0);
			}
			
			if(actor.getY() > mapH){
				actor.move(0, -mapH);
			} else if(actor.getY() < 0){
				actor.move(0, mapH);
			}
		}
	}

	public void addActor(Actor actor){
		actor.setGame(this);
		actors.add(actor);
		actor.init();
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput(){
		return input;
	}
}
