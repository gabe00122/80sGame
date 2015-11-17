package asteroids;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class Shot extends Actor{
	
	private static final BasicStroke STROKE = new BasicStroke(2);
	private static final Color COLOR = Color.YELLOW;
	
	private static final double LENGTH = 10;
	private static final double MAX_AGE = 2;
	private static final double SPEED = 650;
	
	private double frontX;
	private double frontY;
	
	private double age;
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(COLOR);
		g.setStroke(STROKE);
		
		g.drawLine((int)(getX()+frontX), (int)(getY()+frontY),
				   (int)(getX()-frontX), (int)(getY()-frontY));
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		
		age += delta;
		if(age > MAX_AGE){
			destroy();
		}
		
		List<Actor> col = getGame().checkCollisons(new Rectangle2D.Double(getX()-2.5 + frontX, getY()-2.5 + frontY, 5, 5));
		
		for(int i = 0; i < col.size();i++){
			Actor other = col.get(i);
			if(other instanceof Asteroid){
				other.destroy();
				destroy();
			} else if(other instanceof Ship){
				if(age > 1){
					other.destroy();
				}
			}
		}
	}
	
	@Override
	public void init() {
		age = 0;
		excelerate(getAng(), SPEED);
		double r = Math.toRadians(getAng());
		frontX = Math.cos(r) * LENGTH;
		frontY = Math.sin(r) * LENGTH;
	}
}
