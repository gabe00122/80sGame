package pacman;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Resources {
	public static Image ship;
	public static Image ghostB, ghostR, ghostP, ghostO, ghostS;
	public static String[] map;

	public static void load(){
		try {
			ship = ImageIO.read(new File("assets/pacman.png"));
			ghostB = ImageIO.read(new File("assets/blueGhost.png"));
			ghostO = ImageIO.read(new File("assets/orangeGhost.png"));
			ghostR = ImageIO.read(new File("assets/redGhost.png"));
			ghostP = ImageIO.read(new File("assets/pinkGhost.png"));
			
			ghostS = ImageIO.read(new File("assets/scaredGhost.png"));
			
			map = loadTextMap(new File("assets/map.txt"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String[] loadTextMap(File file) throws FileNotFoundException{
			Scanner in = new Scanner(file);
			
			ArrayList<String> inList = new ArrayList<>();
			while (in.hasNextLine()) {
				inList.add(in.nextLine());
			}
			
			in.close();
			
			return inList.toArray(new String[inList.size()]);
	}
}
