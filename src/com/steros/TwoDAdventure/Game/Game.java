package com.steros.TwoDAdventure.Game;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

/*
 * This is the main file that runs the game. Right now, there is window code in here which will later
 *  be moved to a UI package.
 */

public class Game {
	public static final int HEIGHT = 120;
	public static final int WIDTH = 160;
	private static final int SCALE = 3;
	private static long window;
	
	
	public static void main(String[] args) {
		//Throw error if LWJGL libs are not initializing
		if(!glfwInit()) {
			throw new IllegalStateException("Illegal State: GLFW must be present and initialized!");
		}
		
		//Don't really know what this does yet
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		//Initializes the window object that comes with the game (it was declared at the top of the class)
		window = glfwCreateWindow(WIDTH * SCALE, HEIGHT * SCALE, "Bruth: Adventures of Mithia", 0, 0);

		//Throw error if there is no window or if it failed to create. Can't have a game without a window
		if(window == 0) {
			throw new IllegalStateException("There must be a window to run the game. Maybe it failed to create?");
		}
		
		//Get the video mode of the computer's primary/default monitor
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		System.out.println("Montior Width: " + videoMode.width());
		System.out.println("Monitor Height: " + videoMode.height());
		System.out.println("Game Width: " + (WIDTH * SCALE));
		System.out.println("Game Height: " + (HEIGHT * SCALE));
		
		//Set the width and height of the window and position it in the middle of the screen
		glfwSetWindowPos(window, (videoMode.width() - WIDTH * SCALE) / 2, (videoMode.height() - HEIGHT * SCALE) / 2);
		
		//Display the window to the screen
		glfwShowWindow(window);
		
		//As long as the window has not been told to close, keep polling for work to do. (keeps the window open at all times)
		while(!glfwWindowShouldClose(window)) {
			glfwPollEvents();
		}
		
		//Destroy window objects if the close command has been sent to the window
		glfwTerminate();
	}
}
