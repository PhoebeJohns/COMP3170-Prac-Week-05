package comp3170.week5;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL41.*;

import java.io.File;
import java.io.IOException;

import comp3170.OpenGLException;
import comp3170.Shader;
import comp3170.ShaderLibrary;
import comp3170.IWindowListener;
import comp3170.Window;

public class Week5 implements IWindowListener {

	private Window window;

	private long oldTime;

	final private File DIRECTORY = new File("src/comp3170/week5/shaders");

	private Scene scene;

	int width = 1000;
	int height = 1000;

	public Week5() throws OpenGLException {

		window = new Window("Week 5 prac", width, height, this);
		window.setResizable(true);
		window.run();
		oldTime = System.currentTimeMillis();
	}

	@Override
	/**
	 * Initialise the window
	 */
	public void init() {
		new ShaderLibrary(DIRECTORY);
		scene = new Scene();

		glClearColor(0.0f, 0.14f, 0.27f, 1.0f); // MILLENIUM BLUE

		
	}

	@Override
	/**
	 * Called when the window is redrawn
	 */
	public void draw() {
		glClear(GL_COLOR_BUFFER_BIT);
		update();
		scene.draw(); // Draw the scene, which will draw all its children.
	}
	
	public void update(){
		long time = System.currentTimeMillis();
		float deltaTime = (time - oldTime) / 1000f;
		oldTime = time;
		scene.update(deltaTime);
	}
	@Override
	/**
	 * Called when the window is resized
	 */
	public void resize(int width, int height) {
		// record the new width and height
		this.width = width;
		this.height = height;
		glViewport(0, 0, width, height);
	}

	@Override
	/**
	 * Called when we close of the window
	 */
	public void close() {

	}

	public static void main(String[] args) throws IOException, OpenGLException {
		new Week5();
	}

}
