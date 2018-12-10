package bruth.game;
import static org.lwjgl.glfw.GLFW.*;

import java.io.IOException;

import javax.xml.soap.Text;

import bruth.game.gfx.Sprite;
import bruth.game.gfx.Window;
import bruth.game.engine.io.*;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL46.*;

/*
 * This is the main file that runs the game. It just creates a window with the information passed to it. 
 * Later, the window information will come from a config file and this class will do more including starting the sequencer thread (game loop).
 */

public class Game {
	static Thread gameThread;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		GameSave gs = new GameSave();
		gs.First_Save();
		
		//Instantiate a Window object and use GLFW magic to build and display it (it's parameters will come from a config file later). 
		Window window = new Window(160, 120, 3, "Bruth: Adventures of Mithia", glfwGetPrimaryMonitor());
		window.create();
		
		//Sets the OpenGL Context of the window to the current OpenGL context so the game knows where to draw things
		glfwMakeContextCurrent(window.getGLFWWindowID());
		
		//Gets the OpenGL current context (the context of our window, as written above) ready to render GFX!!
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		
		Sprite sprite = new Sprite("res/sprites.png");
		
		
		//As long as the close window command hasn't been sent, keep looking for work to do. 
		//Currently, it will never find any, but, it will never stop trying until you hit close.
		while(!glfwWindowShouldClose(window.getGLFWWindowID())) {
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			sprite.bind();
			
			/*glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2f(-0.5f, 0.5f);
				
				glTexCoord2f(1, 0);
				glVertex2f(0.5f, 0.5f);
				
				glTexCoord2f(1, 1);
				glVertex2f(0.5f, -0.5f);
				
				glTexCoord2f(0, 1);
				glVertex2f(-0.5f, -0.5f);
			glEnd();*/
			
			glfwSwapBuffers(window.getGLFWWindowID());
		}
		
		//Destorys the window. The program can only get this far is the above loop is broken out of (user closed window)
		window.destroy();
	}
}
