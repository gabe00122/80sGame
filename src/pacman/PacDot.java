package pacman;

import java.awt.Color;
import java.awt.Graphics2D;

public class PacDot extends Actor {
	private boolean megaDot;
	
	public PacDot(double x, double y, boolean mega) {
		megaDot = mega;
		if(mega){
			setSize(15, 15);
		}else{
			setSize(10, 10);
		}
		setPosition(x, y);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillOval((int)(getX()-getWidth()/2), (int)(getY()-getHeight()/2), (int)getWidth(), (int)getHeight());
	}
	
	@Override
	public void update(double delta) {
		
	}
	
	public void eat(){
		getGame().removeActor(this);
		getGame().removePacDot();
		
		if(megaDot){
			for(Ghost g: getGame().getGhosts()){
				g.scare();
			}
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
