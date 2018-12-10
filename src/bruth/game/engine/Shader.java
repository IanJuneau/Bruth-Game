package bruth.game.engine;

import static org.lwjgl.opengl.GL46.*;

import java.io.IOException;

public class Shader {
	private int program;
	private int vs;
	private int fs;
	
	public Shader(String fileName) {
		program = glCreateProgram();
		
		vs = glCreateShader(GL_VERTEX_SHADER);
		
		
	}
	
	private String readFile(String fileName) throws IOException {
		StringBuilder string = new StringBuilder();
		return string.toString();
	}
}
