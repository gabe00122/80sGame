package swinggames;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

/**
 * Represents a referents to an image.
 * Can have rotation translation and scaling to a size.
 */
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
	
	/**
	 * Create a new sprint, dose not copy the images.
	 * @param image
	 */
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
	
	/**
	 * Changes the image but not anything else about the sprite.
	 * @param image
	 */
	public void setImage(Image image){
		this.image = image;
	}
	
	/**
	 * Sets the position on the screen
	 * @param x in pixels
	 * @param y in pixels
	 */
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param d in degrees
	 */
	public void setRotation(double d){
		r = Math.toRadians(d);
	}
	
	/**
	 * Sets the size the image will be stretched to.
	 * @param width in pixels
	 * @param height in pixels
	 */
	public void setSize(double width, double height){
		this.width = width;
		this.height = height;
		xScale = width / image.getWidth(null);
		yScale = height / image.getHeight(null);
	}
	
	/**
	 * draws the image with the transformations the sprite has.
	 * @param g the graphics object to draw with
	 */
	public void draw(Graphics2D g){
		transform.setToIdentity();
		transform.translate(x - width/2, y - width/2);
		transform.rotate(r, width/2, height/2);
		transform.scale(xScale, yScale);
		
		g.drawImage(image, transform, null);
	}
}
