

import javax.swing.JFrame;

import gabe00122.swinggames.GameDisplay;

public class Main {
	public static void main(String[] args){
		Resources.load();
		
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("pacman");
		
		
		TestGame test = new TestGame();
		GameDisplay display = new GameDisplay(test);
		display.setTargetFps(60);
		
		frame.add(display);
		frame.setVisible(true);
		
		display.start();
	}
}
