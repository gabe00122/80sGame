package swinggames;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Sprite {
	private Image image;
	private AffineTransform transform;
	private double x;
	private double y;
	private double r;
	private double width;
	private double height;
	private double xScale;
	private double yScale;
	
	public Sprite(Image image){
		this.image = image;
		transform = new AffineTransform();
		
		x = 0;
		y = 0;
		r = 0;
		width = image.getWidth(null);
		height = image.getHeight(null);
		xScale = 1;
		yScale = 1;
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setRotation(double d){
		r = Math.toRadians(d);
	}
	
	public void setSize(double width, double height){
		this.width = width;
		this.height = height;
		xScale = width / image.getWidth(null);
		yScale = height / image.getHeight(null);
	}
	
	public void draw(Graphics2D g){
		transform.setToIdentity();
		transform.translate(x - width/2, y - width/2);
		transform.rotate(r, width/2, height/2);
		transform.scale(xScale, yScale);
		
		g.drawImage(image, transform, null);
	}
}
