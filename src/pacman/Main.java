package pacman;
import java.awt.Color;

import javax.swing.JFrame;
import swinggames.GameDisplay;

public class Main {
	public static void main(String[] args){
		Resources.load();
		
		JFrame frame = new JFrame();
		
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("pacman");
		frame.setBackground(Color.BLACK);
		
		
		PacmanGame test = new PacmanGame();
		GameDisplay display = new GameDisplay(test, 600, 600);
		display.setTargetFps(60);

		frame.add(display);
		frame.setVisible(true);
		
		display.start();
	}
}
