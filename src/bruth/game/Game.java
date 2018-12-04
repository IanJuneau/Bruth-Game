package bruth.game;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import bruth.game.gfx.Window;
import bruth.game.engine.GameThread;

/*
 * This is the main file that runs the game. It just creates a window with the information passed to it. 
 * Later, the window information will come from a config file and this class will do more including starting the sequencer thread (game loop).
 */

public class Game {
	static Thread gameThread;
	
	public static void main(String[] args) throws InterruptedException {
		gameThread = new GameThread();
		gameThread.start();
		gameThread.join();
		
		//Instantiate a Window object and use GLFW magic to build and display it (it's parameters will come from a config file later). 
		Window window = new Window(160, 120, 3, "Bruth: Adventures of Mithia", glfwGetPrimaryMonitor());
		window.create();
		
		//As long as the close window command hasn't been sent, keep looking for work to do. 
		//Currently, it will never find any, but, it will never stop trying until you hit close.
		while(!glfwWindowShouldClose(window.getGLFWWindowID())) {
			glfwPollEvents();
		}
		
		//Destorys the window. The program can only get this far is the above loop is broken out of (user closed window)
		window.destroy();
	}
}
