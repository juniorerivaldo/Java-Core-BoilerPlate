package Core;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	// classe que vai fazer o handler de todos os gameobjects
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private boolean up = false, down = false, left = false, right = false; // keys
	
	public void tick() {
		
		// passa por cada objeto e atualiza a tela deles
		for(int i =0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
		
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void render(Graphics g) {
		
		// passa por cada objeto e desenha eles
		for(int i =0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject tempObject) {
		
		object.add(tempObject);
		
	}
	
	public void removeObject(GameObject tempObject) {
		
		object.remove(tempObject);
		
	}
	
}
