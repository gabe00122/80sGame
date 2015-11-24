package swinggames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * A game display panel which contains and updates a game.
 */
public class GameDisplay extends JComponent implements KeyListener, ComponentListener, Runnable{
	private static final long serialVersionUID = 1L;
	
	private static final double SECOND = 1000000000;
	
	private static final Font FPS_FONT = new Font(Font.DIALOG, Font.PLAIN, 20);
	
	private Input keyboard;
	private int targetFps;
	private long lastUpdateTime;
	
	private int fpsTrackerTime;
	private int fpsTrackerFrames;
	private int currentFPS;
	
	private Game game;
	
	private int w, h;
	private AffineTransform trans;
	
	/**
	 * 
	 * @param game the game object that is to be displayed
	 * @param width the simulated width of the panel
	 * @param height the height width of the panel
	 */
	public GameDisplay(Game game, int width, int height) {
		setFocusable(true);
		addKeyListener(this);
		addComponentListener(this);
		setDoubleBuffered(true);
		
		w = width;
		h = height;
		
		keyboard = new Input();
		targetFps = 60;
		lastUpdateTime = 0;
		
		fpsTrackerTime = 0;
		fpsTrackerFrames = 0;
		
		trans = new AffineTransform();
		
		this.game = game;
		game.setInput(keyboard);
	}
	
	/*
	 * set
	 */
	public void setTargetFps(int fps){
		targetFps = fps;
	}
	
	private void updateTransform(){
		double xScale = (double)getWidth()/w;
		double yScale = (double)getHeight()/h;
		
		trans.setToIdentity();
		if(xScale < yScale){
			trans.translate(0, (getHeight() - h * xScale)/2.0);
			trans.scale(xScale, xScale);
		} else {
			trans.translate((getWidth() - w * yScale)/2.0, 0);
			trans.scale(yScale, yScale);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		AffineTransform saveX = g2.getTransform();
		g2.setTransform(trans);
		g2.clipRect(0, 0, w, h);
		
		game.draw(g2);
		
		g2.setClip(null);
		g2.setTransform(saveX);
		
		drawFps(g2);
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		keyboard.keyDown(e.getKeyCode());
	}


	@Override
	public void keyReleased(KeyEvent e) {
		keyboard.keyUp(e.getKeyCode());
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void run() {
		lastUpdateTime = System.nanoTime();
		while(true){
			long beforeTime = System.nanoTime();
			
			fpsTrackerFrames++;
			
			int delta = (int)(beforeTime - lastUpdateTime);
			lastUpdateTime = beforeTime;
			updateGame(1.0/targetFps);
			
			fpsTrackerTime += delta;
			if(fpsTrackerTime > SECOND){
				currentFPS = (int)(fpsTrackerFrames/(fpsTrackerTime/SECOND));
				
				fpsTrackerFrames = 0;
				fpsTrackerTime = 0;
			}
			
			long afterTime = System.nanoTime();
			
			long sleepTime = ((int)SECOND/targetFps) - (afterTime - beforeTime);
			
			if(sleepTime > 0){
				try {
					Thread.sleep(sleepTime / 1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void drawFps(Graphics2D g){
		g.setColor(Color.WHITE);
		g.setFont(FPS_FONT);
		g.drawString("FPS: " + currentFPS, 10, 20);
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	public void updateGame(double deltaTime){
		game.update(deltaTime);
		
		repaint();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		updateTransform();
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
	
}
