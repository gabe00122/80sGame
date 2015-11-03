import java.awt.Graphics2D;

public abstract class Actor {
	private TestGame game;
	private double x;
	private double y;
	private double weight;
	private double hight;
	
	public Actor(){
		x = 0;
		y = 0;
		weight = 0;
		hight = 0;
	}
		
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void move(double changeX, double changeY){
		x += changeX;
		y += changeY;
	}

	public double getHight() {
		return hight;
	}

	public void setHight(double hight) {
		this.hight = hight;
	}
	
	public void setSize(double w, double h){
		weight = w;
		hight = h;
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setGame(TestGame game){
		this.game = game;
	}
	
	public TestGame getGame(){
		return game;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract void update(double delta);
}
