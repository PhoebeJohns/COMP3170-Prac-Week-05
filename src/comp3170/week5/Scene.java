package comp3170.week5;

import java.awt.Color;

import comp3170.SceneObject;
import comp3170.week5.sceneobjects.Circle;

public class Scene extends SceneObject {

	private static Scene root;
	private Circle sun;

	public Scene() {
		// construct objects and attach to the scene-graph
		root = this;

		sun = new Circle(); // Create the sun
		sun.setColour(Color.YELLOW);
		sun.setParent(root); // Place the sun in the scene graph.
	}
}
