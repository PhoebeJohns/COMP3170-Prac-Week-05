package comp3170.week5.sceneobjects;
import org.joml.Vector3f;

import comp3170.SceneObject;
import comp3170.week5.Scene;

public class SystemObject extends SceneObject {
	protected float rotationSpeed;
	
	public SystemObject (SceneObject... children) {
		this(0, new Vector3f(), 1f, children);
	}
	
	public SystemObject(float rotationSpeed) {
		this(rotationSpeed, new Vector3f(), 1f);
	}
	
	public SystemObject(float rotationSpeed, SceneObject...children) {
		this(rotationSpeed, new Vector3f(), 1f, children);
	}
	
	public SystemObject(float rotationSpeed, float scale, SceneObject...children) {
		
	}
	
	public SystemObject(float rotationSpeed, Vector3f translation, float scale, SceneObject... children) {

		this.rotationSpeed = rotationSpeed;
		
		getMatrix().translate(translation);
		getMatrix().scale(scale);
		
		for (SceneObject obj : children) {
			obj.setParent(this);
			System.out.println(obj.getParent());
		}
	}
	
	private Vector3f angle = new Vector3f();
	@Override
	public void update(float deltaTime) {
		getMatrix().rotateZ(rotationSpeed * deltaTime);
		super.update(deltaTime);
		getMatrix().getEulerAnglesXYZ(angle);
		System.out.println(angle);
	}
}
