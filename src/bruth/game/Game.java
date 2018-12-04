package bruth.game;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

import bruth.game.gfx.Window;

/*
 * This is the main file that runs the game. Right now, there is window code in here which will later
 *  be moved to a UI package.
 */

public class Game {
	
	
	
	public static void main(String[] args) {
		Window ui = new Window();
		
		
		ui.window();
	}
}
