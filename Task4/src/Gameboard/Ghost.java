package Gameboard;

import Geom.Point3D;

/**
 * represents a Ghost Character
 * with Geographic point
 */
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
