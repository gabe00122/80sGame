package asteroids;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class Actor {
	private AsteroidsGame game;
	private Rectangle2D.Double rect;
	private double speedX;
	private double speedY;
	private double ang;
	private double angSpeed;
	
	public Actor(){
		rect = new Rectangle2D.Double(0, 0, 0, 0);
		speedX = 0;;
		speedY = 0;
		ang = 0;
	}
	
	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getAng() {
		return ang;
	}

	public void setAng(double ang) {
		this.ang = ang;
	}
	
	public double getAngSpeed() {
		return angSpeed;
	}

	public void setAngSpeed(double angSpeed) {
		this.angSpeed = angSpeed;
	}

	public double getX() {
		return rect.x;
	}

	public void setX(double x) {
		rect.x = x;
	}

	public double getY() {
		return rect.y;
	}

	public void setY(double y) {
		rect.y = y;
	}

	public double getWidth() {
		return rect.width;
	}

	public void setWidth(double width) {
		rect.width = width;
	}

	public void move(double changeX, double changeY){
		rect.x += changeX;
		rect.y += changeY;
	}
	
	public void excelerate(double ang, double speed){
		double r = Math.toRadians(ang);
		speedX += Math.cos(r) * speed;
		speedY += Math.sin(r) * speed;
	}
	
	public double getHeight() {
		return rect.height;
	}

	public void setHeight(double height) {
		rect.height = height;
	}
	
	public void setSize(double w, double h){
		rect.width = w;
		rect.height = h;
	}
	
	public void setPosition(double x, double y){
		rect.x = x;
		rect.y = y;
	}
	
	public void setGame(AsteroidsGame game){
		this.game = game;
	}
	
	public AsteroidsGame getGame(){
		return game;
	}
	
	
	public abstract void draw(Graphics2D g);
	public abstract void init();
	public void update(double delta){
		move(speedX * delta, speedY * delta);
		ang += angSpeed * delta;
	}
}
