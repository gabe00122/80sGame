package pacman;


import javax.swing.JFrame;

import swinggames.GameDisplay;

public class Main {
	public static void main(String[] args){
		Resources.load();
		
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("pacman");
		
		
		PacmanGame test = new PacmanGame();
		GameDisplay display = new GameDisplay(test, 800, 800);
		display.setTargetFps(60);
		
		frame.add(display);
		frame.setVisible(true);
		
		display.start();
	}
}
