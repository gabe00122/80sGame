package gabe00122.swinggames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class GameDisplay extends JComponent implements KeyListener, Runnable{
	private static final long serialVersionUID = 1L;
	
	private static final double SECOND = 1000000000;
	
	private Input keyboard;
	private int targetFps;
	private long lastUpdateTime;
	
	private int fpsTrackerTime;
	private int fpsTrackerFrames;
	private int currentFPS;
	
	private Game game;
	
	public GameDisplay(Game game) {
		setFocusable(true);
		addKeyListener(this);
		setDoubleBuffered(true);
		
		keyboard = new Input();
		targetFps = 60;
		lastUpdateTime = 0;
		
		fpsTrackerTime = 0;
		fpsTrackerFrames = 0;
		
		this.game = game;
		game.setInput(keyboard);
	}
	
	public void setTargetFps(int fps){
		targetFps = fps;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		game.draw(g2);
		
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
			updateGame(delta/SECOND);
			
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
		g.setColor(Color.BLACK);
		g.drawString("FPS: " + currentFPS, 10, 20);
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	public void updateGame(double deltaTime){
		game.update(deltaTime);
		
		repaint();
	}
	
}
