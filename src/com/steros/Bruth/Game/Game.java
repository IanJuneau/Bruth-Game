package com.steros.Bruth.Game;
import ui.Window_init;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

/*
 * This is the main file that runs the game. Right now, there is window code in here which will later
 *  be moved to a UI package.
 */

public class Game {
	
	
	
	public static void main(String[] args) {
		Window_init ui = new Window_init();
		
		
		ui.window();
	}
}
