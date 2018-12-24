package Gameboard;

import Geom.Point3D;

public class Ghost extends Character {
	private double radius;
	
	public Ghost(Point3D point ,int id, double speed, double radius) {
		super(point,id,speed);
		this.radius = radius; // Radius eaten
	}
	
	public double getRadius() {
		return radius;
	}
	
}
