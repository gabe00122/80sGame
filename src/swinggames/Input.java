package swinggames;

import java.util.HashSet;
import java.util.Set;

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
	
	public boolean isKeyDown(int keyCode){
		return keys.contains(keyCode);
	}
}
