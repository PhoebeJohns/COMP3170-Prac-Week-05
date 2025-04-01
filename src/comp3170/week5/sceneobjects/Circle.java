package comp3170.week5.sceneobjects;

import static org.lwjgl.opengl.GL41.*;

import java.awt.Color;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import comp3170.GLBuffers;
import comp3170.Shader;
import comp3170.ShaderLibrary;
import comp3170.SceneObject;
import static comp3170.Math.TAU;

public class Circle extends SystemObject {

	private static final String VERTEX_SHADER = "vertex.glsl";
	private static final String FRAGMENT_SHADER = "fragment.glsl";
	public static int NSIDES = 7;

	private Shader shader;
	private Vector4f[] vertices;
	private int vertexBuffer;
	private int[] indices;
	private int indexBuffer;

	private float[] colour = { 0.0f, 0.0f, 0.f };
	
	public Circle() {
		this(0f, new Color(0), new Vector3f(), 1f);
	}
	
	public Circle(float rotationSpeed){
		this(rotationSpeed,new Color(0), new Vector3f(), 1f);
	}

	public Circle(float rotationSpeed, Color col, Vector3f position, float scale) {

		this.rotationSpeed = rotationSpeed;
		setColour(col);
		
		getMatrix().translate(position);
		getMatrix().scale(scale);

		shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);

		//@formatter:off
		// construct a 'circle' (i.e. a polygon with NSIDES) as a list of triangles
		//
		// Vertex 0 is at the centre and the others are anticlockwise around the outside.
		// E.g. for NSIDES = 6
		//
		//   3---2
		//  / \ / \
		// 4---0---1
		//  \ / \ /
		//   5---6
		//@formatter:on

		vertices = new Vector4f[NSIDES + 1];

		vertices[0] = new Vector4f(0, 0, 0, 1); // centre

		Matrix4f rotate = new Matrix4f();

		// construct each vertex by rotating the vector (1,0) by fraction i/n of a full
		// turn

		for (int i = 0; i < NSIDES; i++) {
			float angle = i * TAU / NSIDES;

			rotate.rotationZ(angle); // R = R(angle)
			vertices[i + 1] = new Vector4f(1, 0, 0, 1); // v = (1,0,0)
			vertices[i + 1].mul(rotate); // v = R v
		}

		vertexBuffer = GLBuffers.createBuffer(vertices);

		indices = new int[NSIDES * 3]; // each side creates 1 triangle with 3 vertices

		int k = 0;
		for (int i = 1; i <= NSIDES; i++) {
			indices[k++] = 0;
			indices[k++] = i;
			indices[k++] = (i % NSIDES) + 1; // wrap around when i = NSIDES
		}

		indexBuffer = GLBuffers.createIndexBuffer(indices);
	}

	public void setColour(Color colour) {
		colour.getRGBColorComponents(this.colour);
	}

	@Override
	protected void drawSelf(Matrix4f modelMatrix) {
//		for(int i = 0; i < vertices.length; i++) {
//			System.out.println(vertices[i]);
//		}
		shader.enable();
		shader.setUniform("u_modelMatrix", modelMatrix);
		shader.setAttribute("a_position", vertexBuffer);
		shader.setUniform("u_colour", colour);

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
	}

}
