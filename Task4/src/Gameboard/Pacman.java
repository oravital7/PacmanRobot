package Gameboard;

import Geom.Point3D;

/**
 * This class represents a Pacman Character
 * with Geographic point
 *
 */
public class Pacman extends Character {
	private double radius; 

	public Pacman(Point3D point, int id, double speed, double radius) {
		super(point,id,speed);
		this.radius = radius; // Radius eaten
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

}
