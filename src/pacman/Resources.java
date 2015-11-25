package pacman;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class is used to load the resources used in the game.
 *
 */
public class Resources {
	public static Image pacman;
	public static Image ghostB, ghostR, ghostP, ghostO, ghostS, cherry, orange, strawberry, deadEyes;
	public static AudioClip pacmanDies, eatingGhost, openingSong, eatingFruit, wakaWaka;
	public static String[] map;

	/**
	 * This method loads the resources used
	 * like ghosts pacman and the map.
	 */
	public static void load(){
		try {
			pacman = ImageIO.read(new File("assets/pacman.png"));
			ghostB = ImageIO.read(new File("assets/blueGhost.png"));
			ghostO = ImageIO.read(new File("assets/orangeGhost.png"));
			ghostR = ImageIO.read(new File("assets/redGhost.png"));
			ghostP = ImageIO.read(new File("assets/pinkGhost.png"));
			deadEyes = ImageIO.read(new File("assets/eyes.png"));
			
			cherry 		= ImageIO.read(new File("assets/cherry.png"));
			orange 		= ImageIO.read(new File("assets/orange.png"));
			strawberry 	= ImageIO.read(new File("assets/strawberry.png"));
			
			ghostS = ImageIO.read(new File("assets/scaredGhost.png"));
			
			map = loadTextMap(new File("assets/map.txt"));
			
			pacmanDies 	= Applet.newAudioClip(new URL("file:assets/pacman_dies.wav"));
			eatingGhost = Applet.newAudioClip(new URL("file:assets/eating_ghost.wav"));
			openingSong = Applet.newAudioClip(new URL("file:assets/intro_music.wav"));
			eatingFruit = Applet.newAudioClip(new URL("file:assets/eating_fruit.wav"));
			wakaWaka 	= Applet.newAudioClip(new URL("file:assets/waka_waka.wav"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method reads from the map.txt file and adds components to an array.
	 * @param file The map.txt file
	 * @return array used for creating the map
	 * @throws FileNotFoundException
	 */
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
