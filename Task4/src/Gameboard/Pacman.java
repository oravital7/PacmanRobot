package Gameboard;

import Geom.Point3D;

/**
 * This class represents a Pacman Character
 * with Geographic point
 *
 */
public class Pacman extends Character {
	private double radius, orientation; 
	public boolean destroyed;
	
	public Pacman(Point3D point, int id, double speed, double radius) {
		super(point,id,speed);
		this.radius = radius; // Radius eaten
		this.orientation = 0; // Start orientation with 0 angle
		destroyed = true;
	}
	
	/**
	 * Copy constructor
	 * @param p
	 */
	public Pacman(Pacman p)
	{
		super(p.getPoint(),p.getId(),p.getAttribute());
		this.radius = p.getRadius();
	}

	public double getRadius() {
		return radius;
	}
	
	public double getOrien() {
		return orientation;
	}

	public void setOrien(double angle) {
		orientation = angle;
	}

}
