package demoObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Core.GameObject;
import Core.ID;

public class DemoObj extends GameObject{

	public DemoObj(int x, int y ,ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillOval(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
