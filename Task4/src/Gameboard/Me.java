package Gameboard;

import Geom.Point3D;

public class Me extends Character{
	private double orientation;
	
	public Me(Point3D point, int id, double attribute) {
		super(point, id, attribute);
		this.orientation = 0; // Start orientation with 0 angle
	}

	public double getOrien() {
		return orientation;
	}

	public void setOrien(double angle) {
		orientation = angle;
	}
}
