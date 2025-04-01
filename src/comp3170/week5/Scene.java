package comp3170.week5;

import java.awt.Color;
import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import comp3170.SceneObject;
import comp3170.week5.sceneobjects.Circle;
import comp3170.week5.sceneobjects.SystemObject;

public class Scene extends SceneObject {
	
	private static Scene root;
	private Circle sun;
	
	private SystemObject system = new SystemObject(
			new Circle(1f, new Color(1f,1f,0f), new Vector3f(0,0,0), 0.1f), //sun object
			
			new SystemObject(1/3.65f, new Vector3f(0,0,0), 0.1f,//earth pivot
					new Circle(5f, new Color(0f,0f,1f), new Vector3f(3f,0f,0f), 0.5f), //earth object
					new SystemObject(1f, new Vector3f(3f,0f,0f), 0.5f,//moon pivot
							new Circle(0f, new Color(.4f,.4f,.4f), new Vector3f(2f,0f,0f), 0.4f) //moon object
							)
					),
					

			new SystemObject(1/9f, new Vector3f(0,0,0), 0.1f,//mars pivot
					new Circle(2f, new Color(.8f,.6f,.1f), new Vector3f(7f,0f,0f), 0.5f), //mars object
					new SystemObject(.71f, new Vector3f(7f,0f,0f), 0.5f,//mars moon A pivot
							new Circle(-5f, new Color(.35f,.35f,.35f), new Vector3f(4f,0f,0f), 0.3f) //moon object
							),
					new SystemObject(3.14f, new Vector3f(7f,0f,0f), 0.5f,//mars moon B pivot
							new Circle(2f, new Color(.3f,.3f,.3f), new Vector3f(2f,0f,0f), 0.4f) //moon object
							)
					)
			
			);

	public Scene() {
		
		root = this;
		// construct objects and attach to the scene-graph
		system.setParent(root);

//		sun = new Circle(); // Create the sun
//		sun.setColour(Color.YELLOW);
//		sun.setParent(root); // Place the sun in the scene graph.
	}
	
	public void update(float deltaTime) {
		system.update(deltaTime);
	}
}