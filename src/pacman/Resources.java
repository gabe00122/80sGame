package pacman;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {
	public static Image ship;
	public static Image ghostB, ghostR, ghostP, ghostO, ghostS;
	
	public static void load(){
		try {
			ship = ImageIO.read(new File("assets/ship.png"));
			ship = ImageIO.read(new File("pacman.png"));
			ghostB = ImageIO.read(new File("blueGhost.png"));
			ghostR = ImageIO.read(new File("orangeGhost.png"));
			ghostP = ImageIO.read(new File("redGhost.png"));
			ghostO = ImageIO.read(new File("pinkGhost.png"));
			
			ghostS = ImageIO.read(new File("scaredGhost.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
