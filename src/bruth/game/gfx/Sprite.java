package bruth.game.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL46.*;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Sprite {
	private int id;
	private int width;
	private int height;
	private BufferedImage image;
	private int[] raw_pixels;
	private ByteBuffer pixels;
	
	public Sprite(String filename) throws IOException {
		image = ImageIO.read(new File(filename));
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.raw_pixels = new int[(this.width * this.height * 4)];
		this.raw_pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);
		
		this.pixels = BufferUtils.createByteBuffer(this.width * this.height * 4); 
				
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				int pixel = raw_pixels[i * width + j];
				pixels.put((byte) ((pixel >> 16) & 0xFF)); // RED
				pixels.put((byte) ((pixel >> 8) & 0xFF));  // GREEN
				pixels.put((byte) (pixel & 0xFF));		  // BLUE
				pixels.put((byte) ((pixel >> 24) & 0xFF)); // ALPHA
			}
		}
		
		pixels.flip();
		
		this.id = glGenTextures();
		
		glBindTexture(GL_TEXTURE_2D, this.id);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, this.pixels);
	}
	
	public void bind() {
		this.id = glGenTextures();
	}
}
