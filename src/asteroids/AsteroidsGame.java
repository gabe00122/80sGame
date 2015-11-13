package asteroids;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gabe00122.swinggames.Game;
import gabe00122.swinggames.Input;

public class AsteroidsGame implements Game{
	private static final Font SCORE_FONT = new Font(Font.DIALOG, Font.PLAIN, 20);
	private static final Font GAMEOVER_FONT = new Font(Font.DIALOG, Font.PLAIN, 40);
	
	
	private Input input;
	private List<Actor> actors;
	private double mapW = 800;
	private double mapH = 800;
	private Random rand;
	private int score;
	private int level;
	private boolean playing;
	
	public AsteroidsGame() {
		actors = new ArrayList<>();
		rand = new Random();
		
		reset();
	}
	
	private void reset(){
		actors.clear();
		
		level = 1;
		score = 0;
		playing = true;
		
		Ship ship = new Ship();
		ship.setPosition(400, 400);
		addActor(ship);
		nextLevel();
	}
	
	private void nextLevel(){
		level++;
		for(int i = 0; i < level; i++){
			addActor(new Asteroid(50, mapW * ((double)i/level), 20*i + 20));
		}
	}
	
	public void gameOver(){
		playing = false;
	}
	
	public void checkLevelOver(){
		boolean areAsteroids = false;
		for(int i = 0;i < actors.size(); i++){
			if(actors.get(i) instanceof Asteroid){
				areAsteroids = true;
			}
		}
		
		if(!areAsteroids){
			nextLevel();
		}
	}
	
	public double randomX(){
		return rand.nextDouble() * mapW;
	}
	
	public double randomY(){
		return rand.nextDouble() * mapH;
	}
	
	public Random getRandom(){
		return rand;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.fillRect(0, 0, (int)mapW, (int)mapH);
		for(int i = 0;i < actors.size();i++){
			actors.get(i).draw(g);
		}
		
		g.setFont(SCORE_FONT);
		g.setColor(Color.ORANGE);
		drawCentered(g, "SCORE: "+ score, mapW/2, mapH/4);
		
		if(!playing){
			g.setColor(Color.RED);
			g.setFont(GAMEOVER_FONT);
			drawCentered(g, "GAME OVER", mapW/2, mapH/2);
			drawCentered(g, "press r to restart", mapW/2, mapH*.60);
		}
	}
	
	public void addToScore(int s){
		score += s;
	}
	
	public void drawCentered(Graphics2D g, String s, double x, double y){
		FontMetrics fm = g.getFontMetrics();
		
		g.drawString(s, (float)(x - fm.stringWidth(s)/2), (float)(y - fm.getHeight()/2));
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
			
			if(!playing && input.isKeyDown(KeyEvent.VK_R)){
				reset();
			}
		}
	}
	
	public List<Actor> checkCollisons(Rectangle2D.Double rect){
		List<Actor> collisons = new ArrayList<>();
		for(int i = 0;i < actors.size();i++){
			if(actors.get(i).collide(rect)){
				collisons.add(actors.get(i));
			}
		}
		
		return collisons;
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
