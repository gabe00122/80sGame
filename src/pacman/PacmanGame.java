package pacman;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import gabe00122.swinggames.Game;
import gabe00122.swinggames.Input;

public class PacmanGame implements Game{
	private Input input;
	private List<Actor> actors;
	
	public PacmanGame() {
		actors = new ArrayList<>();
		
		addActor(new Pacman());
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
			actors.get(i).update(delta);
		}
	}

	public void addActor(Actor actor){
		actor.setGame(this);
		actors.add(actor);
	}
	
	@Override
	public void setInput(Input input) {
		this.input = input;
	}
	
	public Input getInput(){
		return input;
	}
}
