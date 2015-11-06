package asteroids;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {
	public static Image ship;
	public static Image asteroid;
	
	public static void load(){
		try {
			ship = ImageIO.read(new File("assets/ship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			asteroid = ImageIO.read(new File("assets/SingleAsteroid.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
