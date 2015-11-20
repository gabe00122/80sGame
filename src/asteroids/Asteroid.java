package asteroids;

import java.awt.Graphics2D;
import java.util.List;

import swinggames.Sprite;

public class Asteroid extends Actor{
	private Sprite sprite;
	private double size;
	private int score;
	
	public Asteroid() {
		size = 100;
		score = 25;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public Asteroid(double x, double y, double size) {
		super();
		this.size = size;
		setScore(25);
		setPosition(x, y);
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		List<Actor> col = getGame().checkCollisons(getRect());
		for(int i = 0;i < col.size(); i++){
			Actor actor = col.get(i);
			if(actor instanceof Ship){
				actor.destroy();
			}
		}
	}
	
	@Override
	public void init() {
		sprite = new Sprite(Resources.asteroid);
		
		setSize(size, size);
		setSpeedX(getSpeedX()+getGame().getRandom().nextInt(100)-50);
		setSpeedY(getSpeedY()+getGame().getRandom().nextInt(100)-50);
		
		setAngSpeed(getGame().getRandom().nextInt(50)-25);
	}
	
	@Override
	public void draw(Graphics2D g) {
		sprite.setPosition(getX(), getY());
		sprite.setRotation(getAng());
		sprite.setSize(getWidth(), getHeight());
		sprite.draw(g);
	}

	public double getSize(){
		return size;
	}
	
	public void setSize(double size){
		this.size = size;
		setSize(size, size);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		getGame().addToScore(score);
		
		if(getSize() > 40){
			int split = 2;
			for(int i = 0;i < split;i++){
				Asteroid ast = new Asteroid();
				ast.setSize(Math.sqrt(Math.pow(size, 2)/split));
				ast.setScore(score*2);
				ast.setPosition(getX(), getY());
				ast.setSpeedX(getSpeedX());
				ast.setSpeedY(getSpeedY());
				getGame().addActor(ast);
			}
		}else{
			getGame().checkLevelOver();
		}
	}
}
