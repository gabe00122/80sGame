package swinggames;

import java.util.HashSet;
import java.util.Set;

/**
 * Stores key board input for later checking
 */
public class Input {
	private Set<Integer> keys;
	
	public Input(){
		keys = new HashSet<>();
	}
	
	protected void keyDown(int keyCode){
		keys.add(keyCode);
	}
	
	protected void keyUp(int keyCode){
		keys.remove(keyCode);
	}
	
	/**
	 * Check if a key is pressed.
	 * @see java.awt.event.KeyEvent
	 * @param keyCode from KeyEvent.VK_*
	 * @return if the key is pressed
	 */
	public boolean isKeyDown(int keyCode){
		return keys.contains(keyCode);
	}
}
