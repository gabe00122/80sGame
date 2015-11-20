package swinggames;

import java.awt.Graphics2D;

public interface Game{
	
	public void draw(Graphics2D g);
	
	public void update(double delta);
	
	public void setInput(Input input);
}
