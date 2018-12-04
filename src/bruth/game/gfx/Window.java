package bruth.game.gfx;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.glfw.GLFWVidMode;

public class Window {
	private final int _windowWidth;
	private final int _windowHeight;
	private final int _windowScale;
	private final String _windowTitle;
	private final long _preferredMonitor;
	private static long _window;
	
	/* Constructors */
	public Window(int width, int height, int scalar, String title, long preferredMonitor) {
		this._windowWidth = width;
		this._windowHeight = height;
		this._windowScale = scalar;
		this._windowTitle = title;
		this._preferredMonitor = preferredMonitor;
	}
	
	/* Public Getters */	
	public int getWidth() {
		return this._windowWidth * this._windowScale;
	}
	public int getHeight() {
		return this._windowHeight * this._windowScale;
	}
	public int getScale() {
		return this._windowScale;
	}
	public String getTitle() {
		return this._windowTitle;
	}
	public long getGLFWWindowID() {
		return this._window;
	}
	
	/* Private Getters */
	private long getPreferredMonitor() {
		return this._preferredMonitor;
	}
	
	/* Public Methods */
	public void create() {
		//Throw error if GLFW libs are not initializing
		if(!glfwInit()) {
			throw new IllegalStateException("GLFW must be present and initialized!");
		}
		
		//Set the window to not be resizable. This will be changed when "full screen" scalable graphics are added
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		//GLFW initializes the window with with the information passed to the constructor. The line after logs that information.
		this._window = glfwCreateWindow(this.getWidth(), this.getHeight(), this.getTitle(), this.getPreferredMonitor(), 0);
		System.out.printf("[Window] Creating window - W: %d H: %d S: %d, Title: %s, Preferred Monitor: %d", this.getWidth(), this.getHeight(), this.getScale(), this.getTitle(), this.getPreferredMonitor());
		
		//Checks to make sure the window didn't fail to initialize
		if(this._window == 0) {
			throw new IllegalStateException("There must be a window to run the game. Maybe it failed to create? (GLFW)");
		}
		
		//GLFW gets the video mode of the computer's primary/default monitor
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		//Log some information for debugging
		System.out.println("Montior Width: " + videoMode.width());
		System.out.println("Monitor Height: " + videoMode.height());
		System.out.println("Game Window Width: " + this.getWidth());
		System.out.println("Game Window Height: " + this.getHeight());
		
		//GLFW positions the given window (this) at the specified point in pixels on the primary monitor of the computer. 
		glfwSetWindowPos(this._window, (videoMode.width() - this.getWidth()) / 2, (videoMode.height() - this.getHeight()) / 2);
		
		//Display the window to the screen
		glfwShowWindow(this._window);
	}
	
	public void destroy() {
		glfwTerminate();
	}
	
}
